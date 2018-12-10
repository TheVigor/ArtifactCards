package com.noble.activity.artifactcards.viewmodels

import android.databinding.BindingAdapter
import android.os.Build
import android.support.v7.widget.CardView
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.noble.activity.artifactcards.imageloader.ImageLoader
import com.noble.activity.artifactcards.utils.setTextFromHtml

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        ImageLoader.get().load(view, imageUrl!!)
    }
}

@BindingAdapter("textFromHtml")
fun bindTextFromHtml(view: TextView, htmlText: String?) {
    view.setTextFromHtml(htmlText)
}

@BindingAdapter("visibilityByValue")
fun bindVisibilityByValue(view: View, value: String?) {
    if (value.isNullOrEmpty()) {
        view.visibility = View.GONE
    } else {
        view.visibility = View.VISIBLE
    }
}

