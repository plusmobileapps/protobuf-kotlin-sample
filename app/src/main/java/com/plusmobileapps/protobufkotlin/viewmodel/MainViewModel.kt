package com.plusmobileapps.protobufkotlin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plusmobileapps.protobufkotlin.network.DogService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import tutorial.DogOuterClass
import tutorial.DogOuterClass.Dog
import tutorial.DogOuterClass.DogsResult

sealed class MainViewState {
    object Loading : MainViewState()
    data class Loaded(val dogs: List<Dog>) : MainViewState()
    data class Error(val message: String) : MainViewState()
}

class MainViewModel(private val api: DogService) : ViewModel() {

    private val _state = MutableStateFlow<MainViewState>(MainViewState.Loading)
    val state: StateFlow<MainViewState> get() = _state

    init {
        fetchDogs()
    }

    fun onRefreshClicked() {
        fetchDogs()
    }

    private fun fetchDogs() {
        _state.value = MainViewState.Loading
        viewModelScope.launch {
            _state.value = try {
                val dogsResult: DogsResult = api.getDogs()
                MainViewState.Loaded(dogsResult.dogsList)
            } catch (e: Exception) {
                MainViewState.Error(e.message.toString())
            }
        }
    }
}