package com.dijitlab.kopiketopos.api

import com.dijitlab.kopiketopos.Constants
import com.dijitlab.kopiketopos.models.ItemListResponse
import com.dijitlab.kopiketopos.models.LoginRequest
import com.dijitlab.kopiketopos.models.LoginResponse
import com.dijitlab.kopiketopos.models.UserResponse
import retrofit2.Call
import retrofit2.http.*

/**
 * Interface for defining REST request functions
 */
interface ApiService {

    @POST(Constants.LOGIN_URL)
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @GET(Constants.USER_URL)
    fun fetchUser(): Call<UserResponse>

    @GET(Constants.ITEM_LIST_URL)
    fun fetchItemList(): Call<ItemListResponse>
}