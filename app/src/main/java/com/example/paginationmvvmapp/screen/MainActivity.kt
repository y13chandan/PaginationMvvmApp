package com.example.paginationmvvmapp.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.paginationmvvmapp.R
import com.example.paginationmvvmapp.adapter.HomePageAdapter
import com.example.paginationmvvmapp.databinding.ActivityMainBinding
import com.example.paginationmvvmapp.libs.toast
import com.example.paginationmvvmapp.model.Failed
import com.example.paginationmvvmapp.model.HomePageResponse
import com.example.paginationmvvmapp.model.Success
import com.example.paginationmvvmapp.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val homeViewModel: HomeViewModel by viewModels()
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding?.mViewModel = homeViewModel
        binding?.lifecycleOwner = this
        binding?.rvHome?.layoutManager = GridLayoutManager(this,2)
        homeViewModel.getHomepageData()
        setObservers()
    }

    private fun setObservers() {
        homeViewModel.contracts.observe(this, { t ->
            when(t) {
                is Success -> ((t as Success).data as HomePageResponse).data.sessions.let {
                    binding?.rvHome?.adapter = HomePageAdapter(it)
                }
                is Failed -> toast(t.error)
            }
        })
    }
}