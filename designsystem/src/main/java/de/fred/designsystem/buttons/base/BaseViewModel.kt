package de.fred.designsystem.buttons.base

import android.util.Log
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<T : ViewModel> : ViewModel() {

    private val className: String? = this::class.simpleName

    init {
        Log.d("ViewModel", "$className wurde initialisiert...")
    }

    override fun onCleared() {
        Log.d("ViewModel", "$className wurde abgeräumt...")
        super.onCleared()
    }
}