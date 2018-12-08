package com.noble.activity.artifactcards.viewmodels

import android.databinding.BindingAdapter
import android.os.Build
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.noble.activity.artifactcards.imageloader.ImageLoader

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        ImageLoader.get().load(view, imageUrl!!)
    }
}

@BindingAdapter("textFromHtml")
fun bindTextFromHtml(view: TextView, htmlText: String?) {
    if (!htmlText.isNullOrEmpty()) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            view.text = Html.fromHtml(htmlText, Html.FROM_HTML_MODE_COMPACT)
        } else {
            view.text = Html.fromHtml(htmlText)
        }
    }
}