package com.example.aceledacomposeui.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.getTextAfterSelection
import androidx.compose.ui.text.input.getTextBeforeSelection
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.aceledacomposeui.R
import org.burnoutcrew.reorderable.BuildConfig
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException


val fontFamily = FontFamily(Font(R.font.lexend_deca_regular_title))

fun String.logDebug(key : String ?= null) {
    if (BuildConfig.DEBUG) {
        Log.d(key ?: "ComposeLogDebug", this)
    }
}

@SuppressLint("DiscouragedApi")
fun Context.fileNameToDrawer(imageName : String) : Int{
    return this.resources.getIdentifier(imageName, "drawable", this.packageName)
}

fun Activity.hideKeyboard() {
    val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    //Find the currently focused view, so we can grab the correct window token from it.
    var view = this.currentFocus
    //If no view currently has focus, create a new one, just so we can grab a window token from it
    if (view == null) {
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Density.localConfigurationView(mIsWidth : Boolean) : Dp {
    Modifier.onGloballyPositioned { coordinates ->
        if (mIsWidth) {
            return@onGloballyPositioned with(this) { (coordinates.size.width.toDp()) }
        } else {
            return@onGloballyPositioned with(this) { (coordinates.size.height.toDp()) }
        }
    }
    return 0.dp
}

fun TextFieldValue.removeText(): TextFieldValue {
    val maxChars = this.text.length
    val textBeforeSelection = this.getTextBeforeSelection(maxChars)
    val textAfterSelection = this.getTextAfterSelection(maxChars)
    val textAfterRemove =
        try {
            textBeforeSelection.substring(0, textBeforeSelection.length - 1)
        } catch (e: IllegalArgumentException) {
            textBeforeSelection
        }

    val newText = "$textAfterRemove$textAfterSelection"
    val newCursorPosition = if (textBeforeSelection.isNotEmpty()){
        textBeforeSelection.length - 1
    } else {
        0
    }

    return TextFieldValue(
        text = newText,
        selection = TextRange(newCursorPosition)
    )
}

fun TextFieldValue.insertText(insertText: String): TextFieldValue {
    val maxChars = this.text.length
    val textBeforeSelection = this.getTextBeforeSelection(maxChars)
    val textAfterSelection = this.getTextAfterSelection(maxChars)
    val newText = "$textBeforeSelection$insertText$textAfterSelection"
    val newCursorPosition = textBeforeSelection.length + insertText.length

    return TextFieldValue(
        text = newText,
        selection = TextRange(newCursorPosition)
    )
}

fun Context.shareLink() {
    val mType = "text/plain"
    val subject = "Bank Account"
    val extraText = "My Bank Account : 0001-09987878-11"
    val shareWith = "ShareWith"

    val intent = Intent(Intent.ACTION_SEND).apply {
        type = mType
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, extraText)
    }

    ContextCompat.startActivity(
        this,
        Intent.createChooser(intent, shareWith),
        null
    )
}

fun Context.saveImageToInternalStorage(uri: Uri) {
    val inputStream = this.contentResolver.openInputStream(uri)
    val outputStream = this.openFileOutput("image.jpg", Context.MODE_PRIVATE)
    inputStream?.use { input ->
        outputStream.use { output ->
            input.copyTo(output)
        }
    }
}

fun Uri.saveFile() {
    val sourceFilename = this.path
    val destinationFilename =
        Environment.getExternalStorageDirectory().path + File.separatorChar + "image.jpg"
    var bis: BufferedInputStream? = null
    var bos: BufferedOutputStream? = null
    try {
        bis = BufferedInputStream(FileInputStream(sourceFilename))
        bos = BufferedOutputStream(FileOutputStream(destinationFilename, false))
        val buf = ByteArray(1024)
        bis.read(buf)
        do {
            bos.write(buf)
        } while (bis.read(buf) != -1)
    } catch (e: IOException) {
        e.printStackTrace()
    } finally {
        try {
            bis?.close()
            bos?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}

fun Uri.convertUriToBitmap(ctx: Context): Bitmap {
    val contentResolver: ContentResolver = ctx.contentResolver
    val stream = contentResolver.openInputStream(this)
    return BitmapFactory.decodeStream(stream)
}


