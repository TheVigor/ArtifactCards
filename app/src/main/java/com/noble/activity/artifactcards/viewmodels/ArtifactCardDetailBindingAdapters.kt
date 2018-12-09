package com.noble.activity.artifactcards.viewmodels

import android.databinding.BindingAdapter
import android.os.Build
import android.text.Html
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

