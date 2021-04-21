package com.example.myrecyclerviewapp

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideImageLoader(private val context: Context) : ImageLoader {
    override fun loadImage(imageUrl: String, imageView: ImageView) {
        Glide.with(context).load(imageUrl).centerCrop().placeholder(R.drawable.ic_launcher_foreground).into(imageView)
    }
}