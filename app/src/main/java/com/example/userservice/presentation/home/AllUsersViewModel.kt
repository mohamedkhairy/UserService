package com.example.userservice.presentation.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userservice.domain.core.Resource
import com.example.userservice.domain.entity.User
import com.example.userservice.domain.useCases.AllUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllUsersViewModel @Inject constructor(private val allUsersUseCase: AllUsersUseCase) :
    ViewModel() {

    val usersList: MutableState<List<User>> = mutableStateOf(ArrayList())
    val loading: MutableState<Boolean> = mutableStateOf(false)
    val isError: MutableState<Boolean> = mutableStateOf(false)

    init {
        getAllUsers()
    }

/**
 * this method can emit 3 state to UI
 * Loading -> when the app trying to get data
 * Success -> when data return successfully
 * Error -> will return old data if it's available or Error if not
 * */
    fun getAllUsers() {
        viewModelScope.launch(Dispatchers.Main) {
            allUsersUseCase.invoke().collect {
                when (it) {
                    is Resource.Loading -> {
                        loading.value = true
                        isError.value = false
                    }
                    is Resource.Success -> {
                        loading.value = false
                        isError.value = false
                        it.data?.let { list -> usersList.value = list }
                    }
                    is Resource.Error -> {
                        loading.value = false

                        if (!it.data.isNullOrEmpty()) {
                            usersList.value = it.data
                            isError.value = false
                        } else
                            isError.value = true


                    }
                }
            }
        }
    }





}