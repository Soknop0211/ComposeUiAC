package com.example.aceledacomposeui.view_model

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aceledacomposeui.data.PreferenceManager
import com.example.aceledacomposeui.model.HomeMainList
import com.example.aceledacomposeui.ui.repository.MainRepo
import com.example.aceledacomposeui.ui.repository.MainScreenState
import com.example.aceledacomposeui.utils.Constants.HomeItemList
import com.example.aceledacomposeui.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: MainRepo,
    // private val session: Session
    private val mContext : Application
): ViewModel() {

    private val _state = MutableStateFlow(MainScreenState())
    val state = _state.asStateFlow()

    private var _mHomeList = MutableLiveData<ArrayList<HomeMainList>>()
    var mHomeList: LiveData<ArrayList<HomeMainList>> = _mHomeList

    fun startScanning(){
        viewModelScope.launch {
            repo.startScanning().collect{
                if (!it.isNullOrBlank()){
                    _state.value = state.value.copy(
                        details = it
                    )
                    // Toast.makeText(mContext, it, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    init {
        fetchListHome()
    }

    fun fetchListHome() {
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
