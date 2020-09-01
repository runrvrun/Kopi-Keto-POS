package com.dijitlab.kopiketopos.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dijitlab.kopiketopos.R
import com.dijitlab.kopiketopos.activities.DashboardActivity
import com.dijitlab.kopiketopos.api.ApiClient
import com.dijitlab.kopiketopos.api.SessionManager
import com.dijitlab.kopiketopos.models.LoginRequest
import com.dijitlab.kopiketopos.models.LoginResponse
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        apiClient = ApiClient()
        sessionManager = SessionManager(this)

        btn_login.setOnClickListener {
            val email = et_email.text.toString().trim()
            val password = et_password.text.toString().trim()
            // form validation
            if(email.isEmpty()){
                et_email.error = "Username is required"
                et_email.requestFocus()
                return@setOnClickListener
            }
            if(password.isEmpty()){
                et_password.error = "Password is required"
                et_password.requestFocus()
                return@setOnClickListener
            }

            // check login
            apiClient.getApiService(this).login(LoginRequest(email = email, password = password))
                .enqueue(object : Callback<LoginResponse> {
                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Log.i("dijitlog", t.toString())
                        Toast.makeText(applicationContext,"Server error",Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                        val loginResponse = response.body()

                        if (loginResponse?.access_token != null) {
                            Toast.makeText(applicationContext,"Welcome back",Toast.LENGTH_LONG).show()
                            sessionManager.saveAuthToken(loginResponse.access_token)
                            startActivity(Intent(applicationContext, DashboardActivity::class.java))
                            finish()
                        } else {
                            // Error logging in
                            Toast.makeText(applicationContext,"Login failed",Toast.LENGTH_LONG).show()
                        }
                    }
                })

        }
    }



}
