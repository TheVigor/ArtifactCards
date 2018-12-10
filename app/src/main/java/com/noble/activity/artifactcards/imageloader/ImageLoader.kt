package com.noble.activity.artifactcards.imageloader

import com.noble.activity.artifactcards.imageloader.glide.GlideImpl

object ImageLoader {
    private var imageLoader: IImageLoader? = null

    fun get(): IImageLoader {
        if (imageLoader == null) {
            synchronized(ImageLoader::class.java) {
                if (imageLoader == null) {
                    imageLoader = GlideImpl()
                }
            }
        }
        return imageLoader!!
    }
}
