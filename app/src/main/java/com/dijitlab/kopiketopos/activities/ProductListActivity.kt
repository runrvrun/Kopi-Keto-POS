package com.dijitlab.kopiketopos.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.dijitlab.kopiketopos.R
import com.dijitlab.kopiketopos.api.ApiClient
import com.dijitlab.kopiketopos.api.SessionManager
import com.dijitlab.kopiketopos.models.ItemListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductListActivity : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        apiClient = ApiClient()
        sessionManager = SessionManager(this)

        fetchItemList()
    }

    private fun fetchItemList() {
        apiClient.getApiService(this).fetchItemList()
            .enqueue(object : Callback<ItemListResponse> {
                override fun onFailure(call: Call<ItemListResponse>, t: Throwable) {
                    Log.i("dijitlog", t.toString())
                    Toast.makeText(applicationContext,"Server error", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<ItemListResponse>, response: Response<ItemListResponse>) {
                    // display items in recyclerview
    
                }
            })
    }
}
