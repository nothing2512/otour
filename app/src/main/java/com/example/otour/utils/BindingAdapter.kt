package com.example.otour.utils

import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import coil.api.load
import coil.request.LoadRequestBuilder
import coil.transform.BlurTransformation
import com.example.otour.R

@BindingAdapter("source", "blur", requireAll = false)
fun ImageView.bind(source: Any, blur: Boolean) {

    val builder: LoadRequestBuilder.() -> Unit = if (!blur) {
        { placeholder(R.mipmap.ic_launcher) }
    } else {
        {
            placeholder(R.mipmap.ic_launcher)
            transformations(BlurTransformation(context, 50f))
        }
    }

    when (source) {
        is String -> {
            if (source != "") load(source, builder = builder)
            else load(R.mipmap.ic_launcher, builder = builder)
        }
        is Int -> load(source, builder = builder)
        is Uri -> load(source, builder = builder)
        is Drawable -> load(source, builder = builder)
        else -> load(R.mipmap.ic_launcher, builder = builder)
    }
}