package com.apdallahy.newsapp.UI.adapters.news

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.apdallahy.newsapp.databinding.TopNewsListItemBinding
import com.todayapps.netgrutask.data.models.NewsModel

class TopNewsViewHolder(private val binding:TopNewsListItemBinding):RecyclerView.ViewHolder(binding.root) {
    fun onBind(item:NewsModel){
        binding.newsItem=item
     }
}