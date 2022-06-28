package com.example.userservice.presentation.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userservice.domain.core.Resource
import com.example.userservice.domain.entity.UserInfo
import com.example.userservice.domain.useCases.UserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserInfoViewModel @Inject constructor(private val userInfoUseCase: UserInfoUseCase) :
    ViewModel() {

    val userInfoState: MutableState<UserInfo?> = mutableStateOf(null)
    val loading: MutableState<Boolean> = mutableStateOf(false)
    val isError: MutableState<Boolean> = mutableStateOf(false)

//    init {
//        getUserInfoById()
//    }

/**
 * this method can emit 3 state to UI
 * Loading -> when the app trying to get data
 * Success -> when data return successfully
 * Error -> will return old data if it's available or Error if not
 * */
    fun getUserInfoById(userId: String) {
        viewModelScope.launch(Dispatchers.Main) {
            userInfoUseCase.invoke(userId).collect {
                when (it) {
                    is Resource.Loading -> {
                        loading.value = true
                        isError.value = false
                    }
                    is Resource.Success -> {
                        loading.value = false
                        isError.value = false
                        it.data?.let { list -> userInfoState.value = list }
                    }
                    is Resource.Error -> {
                        loading.value = false
                            isError.value = true


                    }
                }
            }
        }
    }





}