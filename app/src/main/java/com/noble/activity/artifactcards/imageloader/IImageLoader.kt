package com.noble.activity.artifactcards.imageloader

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.request.RequestListener

interface IImageLoader {

    fun load(imageView: ImageView, url: String)
}