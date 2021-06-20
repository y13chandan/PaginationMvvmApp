package com.example.paginationmvvmapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paginationmvvmapp.model.Failed
import com.example.paginationmvvmapp.model.Success
import com.example.paginationmvvmapp.model.UIState
import com.example.paginationmvvmapp.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository): ViewModel() {
    val progress = MutableLiveData<Boolean>()
    val contracts = MutableLiveData<UIState>()


    fun getHomepageData() {
        progress.value = true
        viewModelScope.launch {
            try {
                val response = repository.getHomepageData()
                contracts.value = Success(response)
                progress.value = false
            } catch (e: Exception) {
                e.message?.let { Failed(it) }
                progress.value = false
            }
        }
    }
}