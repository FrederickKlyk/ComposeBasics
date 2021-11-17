package de.fred.composedemo1.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.fred.composedemo1.navigation.Navigator

class MainViewModel(private val navigator: Navigator) : ViewModel() {
    private val _items = MutableLiveData<MutableList<MainViewModelItem>>(mutableListOf())
    val items: LiveData<MutableList<MainViewModelItem>> = this._items


    fun initialize() {
        this._items.value?.addAll(
            listOf(
                MainViewModelItem("Name1", "City1"),
                MainViewModelItem("Name2", "City2"),
                MainViewModelItem("Name3", "City3"),
                MainViewModelItem("Name4", "City4"),
            )
        )
    }

    fun navigateToDetailsView() {
        navigator.navigateTo(Navigator.NavTarget.Detail)
    }
}