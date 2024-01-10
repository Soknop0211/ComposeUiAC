package com.example.aceledacomposeui.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import com.example.aceledacomposeui.R
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException


object Utils {
    fun getImageUri(inContext: Context, inImage: Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path =
            MediaStore.Images.Media.insertImage(inContext.contentResolver, inImage, "Title", null)
        return Uri.parse(path)
    }

    fun convertUriToBitmap(data: Uri?, mActivity: Context): Bitmap? {
        var loadedBitmap: Bitmap? = null
        // Get and resize profile image
        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
        // TRY getActvity() as well if not work
        val cursor = mActivity.contentResolver.query(data!!, filePathColumn, null, null, null)
        cursor!!.moveToFirst()
        val columnIndex = cursor.getColumnIndex(filePathColumn[0])
        val picturePath = cursor.getString(columnIndex)
        cursor.close()
        val options: BitmapFactory.Options
        try {
            loadedBitmap = BitmapFactory.decodeFile(picturePath)
        } catch (e: OutOfMemoryError) {
            try {
                options = BitmapFactory.Options()
                options.inSampleSize = 2
                loadedBitmap = BitmapFactory.decodeFile(picturePath, options)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
        var exif: ExifInterface? = null
        try {
            val pictureFile = File(picturePath)
            exif = ExifInterface(pictureFile.absolutePath)
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: NullPointerException) {
            e.printStackTrace()
            Toast.makeText(mActivity, R.string.unable_to_read_image, Toast.LENGTH_LONG).show()
        }
        var orientation = ExifInterface.ORIENTATION_NORMAL
        if (exif != null) orientation =
            exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)

        if (loadedBitmap == null) return null

        when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> loadedBitmap =
                rotateBitmap(loadedBitmap, 90)

            ExifInterface.ORIENTATION_ROTATE_180 -> loadedBitmap =
                rotateBitmap(loadedBitmap, 180)

            ExifInterface.ORIENTATION_ROTATE_270 -> loadedBitmap =
                rotateBitmap(loadedBitmap, 270)
        }
        return loadedBitmap
    }

    private fun rotateBitmap(bitmap: Bitmap, degrees: Int): Bitmap? {
        val matrix = Matrix()
        matrix.postRotate(degrees.toFloat())
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }
}