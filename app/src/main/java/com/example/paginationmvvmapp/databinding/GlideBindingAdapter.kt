package com.example.paginationmvvmapp.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.paginationmvvmapp.R

class GlideBindingAdapter {
    companion object {
        @BindingAdapter("imageUrl")
        @JvmStatic
        fun setImageResource(imageView: ImageView, imageUrl: String?) {
            Glide
                .with(imageView.context)
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView)
        }
    }
}