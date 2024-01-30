package com.example.aceledacomposeui.view_model

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aceledacomposeui.data.PreferenceManager
import com.example.aceledacomposeui.model.HomeMainList
import com.example.aceledacomposeui.utils.Constants.HomeItemList
import com.example.aceledacomposeui.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mContext : Application
): ViewModel() {

    private var _mHomeList = MutableLiveData<ArrayList<HomeMainList>>()
    var mHomeList: LiveData<ArrayList<HomeMainList>> = _mHomeList

    init {
        fetchListHome()
    }

    private fun fetchListHome() {
        viewModelScope.launch {
            PreferenceManager.getString(HomeItemList, mContext).let {
                if (!TextUtils.isEmpty(it)){
                    val mList : ArrayList<HomeMainList> = Utils.jsonToDataClass(it!!)
                    _mHomeList.value = mList
                } else {
                    val mListDefault = Utils.mainCategory()
                    val jsonString = Utils.encodeToString(mListDefault)
                    PreferenceManager.saveString(HomeItemList, jsonString, mContext)

                    _mHomeList.value = mListDefault

                }
            }
        }
    }

    fun saveHomeScreenList(mString: String) {
        viewModelScope.launch {
            PreferenceManager.saveString(HomeItemList, mString, mContext)
        }
    }
}
