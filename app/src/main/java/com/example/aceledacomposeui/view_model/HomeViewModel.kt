package com.example.aceledacomposeui.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aceledacomposeui.ui.repository.MainRepo
import com.example.aceledacomposeui.ui.repository.MainScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: MainRepo,
    // private val mContext : Application
): ViewModel() {

    private val _state = MutableStateFlow(MainScreenState())
    val state = _state.asStateFlow()


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
}
