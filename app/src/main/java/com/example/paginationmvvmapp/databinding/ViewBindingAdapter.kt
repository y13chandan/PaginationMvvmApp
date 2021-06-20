package com.example.paginationmvvmapp.databinding

import android.view.View
import androidx.databinding.BindingAdapter

class ViewBindingAdapter {
    companion object {
        @BindingAdapter("android:visibility")
        @JvmStatic
        fun setVisibility(view: View, value: Boolean = false) {
            view.visibility = if (value) View.VISIBLE else View.GONE
        }
    }
}