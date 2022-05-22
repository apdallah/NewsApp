package com.apdallahy.newsapp.UI.adapters.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apdallahy.newsapp.databinding.NewsListItemBinding
import com.apdallahy.newsapp.databinding.TopNewsListItemBinding
import com.todayapps.netgrutask.data.models.NewsModel

class NewsAdapter(private val itemViewType: Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val TOP_NEWS_VIEW_TYPE = 0
        const val NEWS_VIEW_TYPE = 1
    }

    private val data = arrayListOf<NewsModel>()
    fun updateData(toUpdate: MutableList<NewsModel>) {
        data.clear()
        data.addAll(toUpdate)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == TOP_NEWS_VIEW_TYPE) {
            return TopNewsViewHolder(TopNewsListItemBinding.inflate(LayoutInflater.from(parent.context)))
        }else {
            return NewsListViewHolder(NewsListItemBinding.inflate(LayoutInflater.from(parent.context)))

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TopNewsViewHolder) {
            holder.onBind(data[position])
        } else if (holder is NewsListViewHolder) {
            holder.onBind(data[position])

        }
    }

    override fun getItemViewType(position: Int): Int {
        return itemViewType
    }

    override fun getItemCount(): Int {
        return data.size
    }
}