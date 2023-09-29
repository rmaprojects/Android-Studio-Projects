package com.rmaprojects.submission1.utils

import android.content.Context
import android.graphics.Bitmap
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.rmaprojects.submission1.data.preferences.UserInfo
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.*

fun showSnackbar(rootLayout: View, message: String, duration: Int) {
    Snackbar.make(rootLayout, message, duration).show()
}

fun getToken() : String {
    return "Bearer " + UserInfo.token
}

fun File.buildImageBodyPart(): MultipartBody.Part {
    val imageReq = this.asRequestBody("image/*".toMediaTypeOrNull())
    return MultipartBody.Part.createFormData(
        "photo",
        this.name,
        imageReq
    )
}

fun Bitmap.convertToFile(context: Context, fileName: String) : File {
    val file = File(context.cacheDir, fileName)
    file.createNewFile()
    val bos = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.JPEG, 75, bos)
    val bitMapData = bos.toByteArray()
    var fos: FileOutputStream? = null
    try {
        fos = FileOutputStream(file)
    } catch (e: FileNotFoundException) {
        e.printStackTrace()
    }
    try {
        fos?.write(bitMapData)
        fos?.flush()
        fos?.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return file
}

fun String.setRequestBody(): RequestBody {
    return this.toRequestBody("multipart/form-data".toMediaTypeOrNull())
}