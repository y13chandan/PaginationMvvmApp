package com.example.paginationmvvmapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paginationmvvmapp.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository): ViewModel() {
    val progress = MutableLiveData<Boolean>()

    fun getHomepageData() {
        progress.value = true
        viewModelScope.launch {
            try {
                progress.value = false
            } catch (e: Exception) {
                e.message?.let {
                }
                progress.value = false
            }
        }
    }
}