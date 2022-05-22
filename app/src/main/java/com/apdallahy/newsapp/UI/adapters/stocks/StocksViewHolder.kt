package com.apdallahy.newsapp.UI.adapters.stocks

import androidx.recyclerview.widget.RecyclerView
import com.apdallahy.newsapp.data.models.StockModel
import com.apdallahy.newsapp.databinding.NewsListItemBinding
import com.apdallahy.newsapp.databinding.StockListItemBinding
import com.apdallahy.newsapp.databinding.TopNewsListItemBinding
import com.todayapps.netgrutask.data.models.NewsModel

class StocksViewHolder(private val binding: StockListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: StockModel) {
        binding.stockItem = item
    }
}