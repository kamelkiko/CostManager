package com.kiko.costmanager.logic.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.kiko.costmanager.R

fun ImageView.setImageUrl(url: String) {
    Glide.with(this)
        .load(url).error(R.drawable.ic_baseline_error_outline_24).placeholder(
            R.drawable.ic_baseline_download_24
        ).into(this)
}

fun List<String>.getFloat(index: Int): Float? {
    return this[index].toFloatOrNull()
}