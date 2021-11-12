package de.fred.composedemo1

import android.util.Log

class MainViewModelItem(val name: String, val city: String) {

    fun onItemClick(){
        Log.d("viewmodel", "Du hast auf $name geklickt")
    }
}