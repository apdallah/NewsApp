package com.apdallahy.newsapp.UI.adapters.news

import androidx.recyclerview.widget.RecyclerView
import com.apdallahy.newsapp.databinding.NewsListItemBinding
import com.apdallahy.newsapp.databinding.TopNewsListItemBinding
import com.todayapps.netgrutask.data.models.NewsModel

class NewsListViewHolder(private val binding:NewsListItemBinding):RecyclerView.ViewHolder(binding.root) {
    fun onBind(item:NewsModel){
        binding.newsItem=item
    }
}