package com.example.digimantra.utils

import android.graphics.drawable.ColorDrawable
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView

object BindingUtil {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImageUrl(view: ShapeableImageView, url: String?) {
        view.context?.let {
            Glide.with(it).load(url).placeholder(ColorDrawable(it.getColor(com.bumptech.glide.R.color.highlighted_text_material_light))).into(view) }
    }
}
