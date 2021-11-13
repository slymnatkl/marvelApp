package com.marvelapp.core.bindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.marvelapp.core.extensions.loadUrl

@BindingAdapter("app:showFromUrl")
fun showFromUrl(view: ImageView, url: String?){
    view.loadUrl(url)
}