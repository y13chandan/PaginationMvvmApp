package com.example.paginationmvvmapp.network

import com.example.paginationmvvmapp.model.HomePageResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("v2/5df79a3a320000f0612e0115")
    suspend fun getHomepageData(): Response<HomePageResponse>

}