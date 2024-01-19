package com.example.aceledacomposeui.utils

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.provider.MediaStore
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.aceledacomposeui.R
import com.example.aceledacomposeui.model.HomeExtraModel
import com.example.aceledacomposeui.model.HomeItemModel
import com.example.aceledacomposeui.model.HomeMainList
import com.example.aceledacomposeui.model.MobilePhonesData
import com.example.aceledacomposeui.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.lang.reflect.Type
import java.nio.charset.StandardCharsets


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

    fun getListHomeMenu(): ArrayList<HomeItemModel> {
        return arrayListOf(
            HomeItemModel(
                "payment",
                "Payments",
                R.drawable.ic_payment,
            ),
            HomeItemModel(
                "top_up",
                "Mobile Top-up",
                R.drawable.ic_mobile_topup,
            ),
            HomeItemModel(
                "transfers",
                "Transfers",
                R.drawable.ic_transfer,
            ),
            HomeItemModel(
                "pay_me",
                "Pay-Me",
                R.drawable.ic_payme,
            ),
            HomeItemModel(
                "scan_qr",
                "Scan QR",
                R.drawable.ic_qrcode_home,
            ),
            HomeItemModel(
                "account",
                "Accounts",
                R.drawable.ic_account_payment,
            ),
            HomeItemModel(
                "deposits",
                "Deposits",
                R.drawable.baseline_auto_graph_24,
            ),
            HomeItemModel(
                "loan",
                "Loans",
                R.drawable.ic_loan,
            ),
            HomeItemModel(
                "quick_cash",
                "Quick Cash",
                R.drawable.ic_quick_cash,
            )
        )
    }

    fun getListExtraHomeMenu(): ArrayList<HomeExtraModel> {
        return arrayListOf(
            HomeExtraModel(
                "smart",
                "Smart",
                "Save recipient information for quick transaction.",
                false,
                R.drawable.ic_favorite,
            ),

            HomeExtraModel(
                "metfone",
                "Metfone",
                "",
                true,
                R.drawable.ic_currency_exchange,
            ),

            HomeExtraModel(
                "cellcard",
                "Cellcard",
                "",
                false,
                R.drawable.ic_dashboard,
            ),

            HomeExtraModel(
                "seatel",
                "Seatel",
                "More discounts and special offer from ACLEDA's Partners.",
                false,
                R.drawable.ic_promotion,
            ),
        )
    }

    fun getListRecentTransfer(): ArrayList<HomeExtraModel> {
        return arrayListOf(
            HomeExtraModel(
                "1",
                "Bo Bora",
                "Save recipient information for quick transaction.",
                false,
                R.drawable.boy_user,
            ),

            HomeExtraModel(
                "2",
                "Sok Pesey Dara Pich",
                "",
                true,
                R.drawable.ic_user_profile,
            ),

            HomeExtraModel(
                "3",
                "Seth",
                "",
                false,
                R.drawable.girl_user,
            ),

            HomeExtraModel(
                "4",
                "Yora",
                "More discounts and special offer from ACLEDA's Partners.",
                false,
                R.drawable.boy_user,
            ),

            HomeExtraModel(
                "5",
                "Peseth",
                "More discounts and special offer from ACLEDA's Partners.",
                false,
                R.drawable.ic_user_profile,
            ),
            HomeExtraModel(
                "6",
                "Khmer",
                "More discounts and special offer from ACLEDA's Partners.",
                false,
                R.drawable.girl_user,
            ),
            HomeExtraModel(
                "1",
                "Bo Bora",
                "Save recipient information for quick transaction.",
                false,
                R.drawable.boy_user,
            ),

            HomeExtraModel(
                "2",
                "Sok Pesey Dara Pich",
                "",
                true,
                R.drawable.ic_user_profile,
            ),

            HomeExtraModel(
                "3",
                "Seth",
                "",
                false,
                R.drawable.girl_user,
            ),

            HomeExtraModel(
                "4",
                "Yora",
                "More discounts and special offer from ACLEDA's Partners.",
                false,
                R.drawable.boy_user,
            ),

            HomeExtraModel(
                "5",
                "Peseth",
                "More discounts and special offer from ACLEDA's Partners.",
                false,
                R.drawable.ic_user_profile,
            ),
            HomeExtraModel(
                "6",
                "Khmer",
                "More discounts and special offer from ACLEDA's Partners.",
                false,
                R.drawable.girl_user,
            ),
        )
    }

    fun getMobileList(mContext: Context): List<MobilePhonesData> {
        val jsonFileString: String? = getJsonFromAssets(mContext, "MobilePhones.json")
        (jsonFileString ?: "null").logDebug("logidebkijeejjjeehe")

        val gson = Gson()
        val listUserType: Type = object :
            com.google.gson.reflect.TypeToken<List<MobilePhonesData?>?>() {}.type

        return gson.fromJson(jsonFileString, listUserType)

    }

    private fun getJsonFromAssets(context: Context, fileName: String?): String? {
        val jsonString: String = try {
            val isAssets = context.assets.open(fileName!!)
            val size = isAssets.available()
            val buffer = ByteArray(size)
            isAssets.read(buffer)
            isAssets.close()
            String(buffer, StandardCharsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return jsonString
    }

    fun mainCategory() : ArrayList<HomeMainList>{
        return arrayListOf(
            HomeMainList("top_main", "Top Main"),
            HomeMainList("recent_transaction", "Recent Transaction"),
            HomeMainList("slider_recommended", "Slider Recommended"),
            HomeMainList("special_service", "Special Service"),
            HomeMainList("special_offer", "Special Offer"),
            HomeMainList("call_center", "Call Center"),
            HomeMainList("advertise", "Advertise")
            )
    }

    inline fun <reified T> encodeFromString(jsonData: String): T {
        return Gson().fromJson(jsonData, T::class.java)
    }

    fun <T> encodeToString(data: T): String {
        return Gson().toJson(data)
    }

    fun jsonToDataClass(data: String): ArrayList<HomeMainList> {
        val dataType = object : TypeToken<ArrayList<HomeMainList>>() {}.type
        return Gson().fromJson(data, dataType)
    }

    fun jsonToDataUserClass(data: String): User {
        val dataType = object : TypeToken<User>() {}.type
        return Gson().fromJson(data, dataType)
    }

    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun hideSoftKeyboard(view: View) {
        val inputMethodManager =
            view.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}