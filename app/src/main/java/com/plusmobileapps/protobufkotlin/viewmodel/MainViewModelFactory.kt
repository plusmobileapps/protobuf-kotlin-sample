package com.plusmobileapps.protobufkotlin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.plusmobileapps.protobufkotlin.di.DI

class MainViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(api = DI.dogService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}