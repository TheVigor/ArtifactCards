package com.noble.activity.artifactcards.viewmodels

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.noble.activity.artifactcards.imageloader.ImageLoader

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        ImageLoader.get().load(view, imageUrl!!)
    }
}