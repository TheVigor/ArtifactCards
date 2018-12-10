package com.noble.activity.artifactcards.imageloader.glide

import android.widget.ImageView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.noble.activity.artifactcards.R
import com.noble.activity.artifactcards.imageloader.IImageLoader

class GlideImpl : IImageLoader {

    private val normalTransitionOptions = DrawableTransitionOptions()
        .crossFade()

    override fun load(imageView: ImageView, url: String) {
        GlideApp.with(imageView.context)
            .load(url)
            .transition(normalTransitionOptions)
            .placeholder(R.drawable.artifact_image_mark)
            .error(R.drawable.artifact_image_mark)
            .into(imageView)
    }

}