package de.fred.composedemo1

import android.util.Log
import androidx.lifecycle.ViewModel

class MainViewModelItem(val name: String, val city: String) : ViewModel() {

    fun onItemClick(){
        Log.d("viewmodel", "Du hast auf $name geklickt")
    }
}