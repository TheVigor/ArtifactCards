package com.noble.activity.artifactcards.utils

import android.view.View

interface OnItemClickListener<T> {
    fun onItemClick(position: Int, bean: T, itemView: View)
}
