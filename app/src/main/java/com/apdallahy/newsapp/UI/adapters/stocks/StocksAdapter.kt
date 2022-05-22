package com.apdallahy.newsapp.UI.adapters.stocks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apdallahy.newsapp.data.models.StockModel
import com.apdallahy.newsapp.databinding.NewsListItemBinding
import com.apdallahy.newsapp.databinding.StockListItemBinding
import com.apdallahy.newsapp.databinding.TopNewsListItemBinding
import com.todayapps.netgrutask.data.models.NewsModel

class StocksAdapter() : RecyclerView.Adapter<StocksViewHolder>() {


    private val data = arrayListOf<StockModel>()
    fun updateData(toUpdate: ArrayList<StockModel>) {
        data.clear()
        data.addAll(toUpdate)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StocksViewHolder {


        return StocksViewHolder(StockListItemBinding.inflate(LayoutInflater.from(parent.context)))


    }

    override fun onBindViewHolder(holder: StocksViewHolder, position: Int) {

        holder.onBind(data[position])


    }


    override fun getItemCount(): Int {
        return data.size
    }
}