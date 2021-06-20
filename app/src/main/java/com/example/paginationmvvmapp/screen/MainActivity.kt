package com.example.paginationmvvmapp.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paginationmvvmapp.R
import com.example.paginationmvvmapp.adapter.HomePageAdapter
import com.example.paginationmvvmapp.databinding.ActivityMainBinding
import com.example.paginationmvvmapp.libs.EndlessRecyclerViewScrollListener
import com.example.paginationmvvmapp.libs.toast
import com.example.paginationmvvmapp.model.Failed
import com.example.paginationmvvmapp.model.HomePageResponse
import com.example.paginationmvvmapp.model.Session
import com.example.paginationmvvmapp.model.Success
import com.example.paginationmvvmapp.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val homeViewModel: HomeViewModel by viewModels()
    private var binding: ActivityMainBinding? = null
    private var adapter: HomePageAdapter? = null
    private var sessions: ArrayList<Session>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding?.mViewModel = homeViewModel
        binding?.lifecycleOwner = this
        initRecyclerView()
        homeViewModel.getHomepageData()
        setObservers()
    }

    private fun setObservers() {
        homeViewModel.contracts.observe(this, { t ->
            when(t) {
                is Success -> callAdapter((t as Success).data as ArrayList<Session>)
                is Failed -> toast(t.error)
            }
        })
    }

    private fun initRecyclerView() {
        val layoutManager = GridLayoutManager(this,2)
        binding?.rvHome?.layoutManager = layoutManager
        val scrollListener = object : EndlessRecyclerViewScrollListener(layoutManager,1) {
            override fun onLoadMore(currentPage: Int) {
                if (homeViewModel.totalPages < 5) {
                    homeViewModel.getHomepageData()
                } else {
                    adapter?.setShouldShowProgressBar(false)
                }
            }
        }
        binding?.rvHome?.addOnScrollListener(scrollListener)
    }

    private fun callAdapter(sessions: ArrayList<Session>) {
        if(adapter == null) {
            adapter = HomePageAdapter(sessions)
            adapter?.setShouldShowProgressBar(true)
            binding?.rvHome?.adapter = adapter
        } else {
            this.sessions?.addAll(sessions)
            adapter?.updateAdapter(sessions)
        }

    }

}