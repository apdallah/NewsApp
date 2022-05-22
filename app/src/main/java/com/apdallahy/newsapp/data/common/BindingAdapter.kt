package com.apdallahy.newsapp.data.common

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.apdallahy.newsapp.R
import com.squareup.picasso.Picasso
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("url")
fun bindImageUrl(imageView: ImageView, url: String?) {
    url?.let {
        Picasso.get()
            .load(it)
            .resize(180, 180)
            .centerCrop()
            .placeholder(R.drawable.loading)
            .error(R.drawable.loading_error)
            .into(imageView)
    }
}
@BindingAdapter("bigUrl")
fun bindBigImageUrl(imageView: ImageView, bigUrl: String?) {
    bigUrl?.let {
        Picasso.get()
            .load(it)
            .fit()
            .centerCrop()
            .placeholder(R.drawable.loading)
            .error(R.drawable.loading_error)
            .into(imageView)
    }
}

@BindingAdapter("date")
fun formatData(textView: TextView, date: String?) {
    date?.let {
        try {
            val formate = SimpleDateFormat("HH:mm yyyy-MM-dd", Locale.US)
            val parsed_date = formate.parse(it)
            textView.setText(parsed_date?.toString() ?: "")
        } catch (e: Exception) {
            textView.setText(it.split('T')[0])

        }

    }
}

@BindingAdapter("price")
fun formatPrice(textView: TextView, price: Double?) {
    price?.let {


        val text = DecimalFormat(
            "#.##",
            DecimalFormatSymbols(Locale.US)
        ).format(it)
        textView.setText(text)


    }
}