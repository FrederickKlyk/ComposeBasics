package de.fred.composedemo1.thirdfeature.ui

import de.fred.composedemo1.navigation.NavTarget
import de.fred.composedemo1.navigation.Navigator
import de.fred.designsystem.base.BaseViewModel

class ThirdFeatureViewModel(
    private val navigator: Navigator,
) : BaseViewModel<ThirdFeatureViewModel>() {
    fun navigateToSecondFeatureModule() {
        navigator.navigateTo(NavTarget.SecondFeatureModule.SecondFeatureWithParams(EXAMPLE_ID))
    }

    fun navigateToShoppingCart() {
        navigator.navigateTo(NavTarget.ShoppingCartModule)
    }

    companion object {
        const val EXAMPLE_ID = "1337"
    }
}
