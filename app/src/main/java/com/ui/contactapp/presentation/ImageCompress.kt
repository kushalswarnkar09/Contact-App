package com.ui.contactapp.presentation

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream
import java.io.OutputStream

fun ImageCompress(imageData : ByteArray): ByteArray {
    val bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.size)
    val outputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 50, outputStream)
    return outputStream.toByteArray()
}