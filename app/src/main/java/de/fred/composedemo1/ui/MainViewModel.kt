package de.fred.composedemo1.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import de.fred.composedemo1.navigation.Navigator
import de.fred.designsystem.buttons.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val navigator: Navigator) : BaseViewModel<MainViewModel>() {
    private val _items = MutableLiveData<MutableList<MainViewModelItem>>(mutableListOf())
    val items: LiveData<MutableList<MainViewModelItem>> = this._items


    fun initialize() {
        this._items.value?.apply {
            clear()
            addAll(
                listOf(
                    MainViewModelItem("Name1", "City1"),
                    MainViewModelItem("Name2", "City2"),
                    MainViewModelItem("Name3", "City3"),
                    MainViewModelItem("Name4", "City4"),
                )
            )
        }
    }

    fun navigateToDetailsView() {
        navigator.navigateTo(Navigator.NavTarget.Detail)
    }
}