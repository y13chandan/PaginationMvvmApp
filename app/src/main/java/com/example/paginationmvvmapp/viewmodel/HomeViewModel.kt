package com.example.paginationmvvmapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paginationmvvmapp.model.*
import com.example.paginationmvvmapp.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository): ViewModel() {
    val progress = MutableLiveData<Boolean>()
    val contracts = MutableLiveData<UIState>()
    var totalPages = 0


    fun getHomepageData() {
        if (totalPages ==  0) {
            progress.value = true
        }
        viewModelScope.launch {
            try {
                val response = repository.getHomepageData()
                contracts.value = Success(response.data.sessions)
                progress.value = false
                totalPages += 1
            } catch (e: Exception) {
                e.message?.let { Failed(it) }
                progress.value = false
            }
        }
    }
}