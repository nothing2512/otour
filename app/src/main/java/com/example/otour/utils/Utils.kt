package com.example.otour.utils

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.otour.R
import com.facebook.shimmer.ShimmerFrameLayout
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun ShimmerFrameLayout.start() {
    show()
    startShimmer()
}

fun ShimmerFrameLayout.stop() {
    hide()
    stopShimmer()
}

fun <VDB : ViewDataBinding> Activity.getBinding(layout: Int): VDB =
    DataBindingUtil.setContentView(this, layout)

fun <VDB : ViewDataBinding> getBinding(
    inflater: LayoutInflater,
    layout: Int,
    parent: ViewGroup?
): VDB =
    DataBindingUtil.inflate(inflater, layout, parent, false)

fun <VDB : ViewDataBinding> getBinding(layout: Int, parent: ViewGroup?): VDB =
    DataBindingUtil.inflate(LayoutInflater.from(parent?.context), layout, parent, false)

fun <T> ViewModel.launchMain(block: suspend CoroutineScope.() -> T) {
    viewModelScope.launch {
        withContext(Dispatchers.Main) {
            block.invoke(this)
        }
    }
}

fun <T> launchMain(block: suspend CoroutineScope.() -> T) {
    CoroutineScope(Dispatchers.Main).launch {
        block.invoke(this)
    }
}

fun Activity.pickImage() {
    ImagePicker.with(this).pick()
}

fun Fragment.pickImage() {
    ImagePicker.with(this).pick()
}

private fun ImagePicker.Builder.pick() {
    this.crop()
        .compress(1024)
        .start(Constants.IMAGE_REQUEST_CODE)
}

fun getPickedImage(requestCode: Int, resultCode: Int, data: Intent?): String? {
    return if (requestCode == Constants.IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
        val file: File? = ImagePicker.getFile(data)
        file?.absolutePath
    } else null
}

fun Activity.toast(msg: String?) {
    Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
}

fun Activity.startActivity(clazz: Class<*>, data: (Intent.() -> Unit)? = null) {
    val intent = Intent(applicationContext, clazz)
    data?.invoke(intent)
    startActivity(intent)
}

fun Fragment.toast(msg: String?) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}

fun getImagePart(key: String, path: String): MultipartBody.Part {
    val file = File(path)
    val body = RequestBody.create(MediaType.parse("image/*"), file)
    return MultipartBody.Part.createFormData(key, file.name, body)
}

fun getTextPart(value: String): RequestBody =
    RequestBody.create(MediaType.parse("text/plain"), value)

fun createAuth(userId: Int = 0) = "$userId:RANolaBIDeflTALa"

fun FragmentActivity.setFragment(view: View, fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
        .replace(view.id, fragment)
        .commit()
}