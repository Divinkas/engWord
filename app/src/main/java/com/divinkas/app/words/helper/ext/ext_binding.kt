package com.divinkas.app.words.helper.ext

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

fun <T : ViewDataBinding> Fragment.bindView(view: View, block: T.() -> Unit = {}): T {
    val binding = DataBindingUtil.bind<T>(view) ?: throw RuntimeException("Can't bind to view")
    binding.lifecycleOwner = viewLifecycleOwner
    binding.apply(block)
    return binding
}

fun <T : ViewDataBinding> View.bindingView(@LayoutRes layout: Int, container: ViewGroup, block: T.() -> Unit = {}): T {
    val binding = DataBindingUtil.inflate<T>(LayoutInflater.from(context), layout, container, false)
    binding.apply(block)
    return binding
}

fun <T : ViewDataBinding> AppCompatActivity.binding(@LayoutRes layout: Int, block: T.() -> Unit = {}): T {
    val binding = DataBindingUtil.setContentView<T>(this, layout) ?: throw RuntimeException("Can't bind to view")
    binding.apply(block)
    return binding
}

@BindingAdapter("urlImage")
fun urlImage(imageView: ImageView, link: String){}