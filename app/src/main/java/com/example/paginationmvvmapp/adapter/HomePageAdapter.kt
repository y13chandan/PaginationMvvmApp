package com.example.paginationmvvmapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.paginationmvvmapp.databinding.InfiniteScrollProgressbarBinding
import com.example.paginationmvvmapp.databinding.RowLayoutMusicBinding
import com.example.paginationmvvmapp.model.Session

class HomePageAdapter(
    private val sessions: List<Session>
) : RecyclerView.Adapter<BaseBindingViewHolder>() {

    private var showProgressBar = false

    companion object {
        const val SESSION = 0
        const val PROGRESSBAR = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseBindingViewHolder {
        return when (viewType) {
            SESSION -> NameViewHolder(RowLayoutMusicBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> ProgressViewHolder(InfiniteScrollProgressbarBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun onBindViewHolder(holder: BaseBindingViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return sessions.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            position < sessions.size -> SESSION
            else -> PROGRESSBAR
        }
    }

//    fun updateAdapter(list: ArrayList<Session>) {
//        val position = sessions.size
//        sessions
//        notifyItemInserted(position)
//    }

    fun setShouldShowProgressBar(showProgress: Boolean) {
        this.showProgressBar = showProgress
        notifyItemChanged(itemCount - 1)
    }

    inner class ProgressViewHolder(private val binding: InfiniteScrollProgressbarBinding) : BaseBindingViewHolder(binding) {
        override fun onBind(item: Any) {
            super.onBind(item)
            binding.show = showProgressBar
            binding.executePendingBindings()
        }
    }

    inner class NameViewHolder(private val binding: RowLayoutMusicBinding) : BaseBindingViewHolder(binding) {
        override fun onBind(item: Any) {
            super.onBind(item)
            binding.session = sessions[item as Int]
//            binding.position = item
//            binding.listener = listener
            binding.executePendingBindings()
        }
    }
}