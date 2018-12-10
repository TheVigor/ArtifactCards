package com.noble.activity.artifactcards.artifact.detail

import android.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView
import android.widget.TextView
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

