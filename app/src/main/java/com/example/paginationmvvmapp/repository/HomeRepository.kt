package com.example.paginationmvvmapp.repository

import com.example.paginationmvvmapp.model.HomePageResponse
import com.example.paginationmvvmapp.network.ApiService
import com.example.paginationmvvmapp.network.SafeApiRequest
import javax.inject.Inject

class HomeRepository @Inject constructor(private val apiService: ApiService): SafeApiRequest() {

    suspend fun getHomepageData(): HomePageResponse {
        return apiRequest { apiService.getHomepageData() }
    }

}