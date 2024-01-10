package com.example.aceledacomposeui.view_model

import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aceledacomposeui.model.NotificationModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(): ViewModel() {

    private val _mNotificationList = MutableLiveData<ArrayList<NotificationModel>>()
    val mNotificationList: LiveData<ArrayList<NotificationModel>> get() = _mNotificationList

    fun requestList(){
        viewModelScope.launch {
            val mList = ArrayList<NotificationModel>()
            for (i in 1..20) {
                mList.add(NotificationModel(
                    i.toString(),
                    "KHQR Payment ACLDA Mobile",
                    "05, Jan, 2024 11:30 AM",
                    "You have made payment 100$ from Mr. Dara Bank by KHQR, on 05, Jan, 2024 11:30 AM. Thanks you for using our service ! You have made payment 100$ from Mr. Dara Bank by KHQR, on 05, Jan, 2024 11:30 AM. Thanks you for using our service !"
                    ))
            }

            _mNotificationList.value = mList
        }
    }


    private val _mShowLess = mutableIntStateOf(2)
    val mShowLess: Int = _mShowLess.intValue

    fun incrementCount() {
        _mShowLess.value = if (_mShowLess.value > 2) {
            2
        } else {
            10
        }
    }

}