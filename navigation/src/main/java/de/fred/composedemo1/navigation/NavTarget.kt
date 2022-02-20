package de.fred.composedemo1.navigation

sealed class NavTarget(val label: String) {
    object Home : NavTarget(ModuleRoutes.Home.label)
    object RootModule : NavTarget(ModuleRoutes.RootModule.label)
    object Detail : NavTarget(ModuleRoutes.Detail.label)

    sealed class SecondFeatureModule {
        data class SecondModuleWithParams(val id: String) : NavTarget("${ModuleRoutes.SecondModule}/$id")
        data class SecondFeatureWithParams(val id: String) : NavTarget("${ModuleRoutes.SecondFeature.label}/$id")
    }

    object ThirdModule : NavTarget(ModuleRoutes.ThirdModule.label)
    object ThirdFeature : NavTarget(ModuleRoutes.ThirdFeature.label)

    object ShoppingCartModule : NavTarget(ModuleRoutes.ShoppingCartModule.label)
    object ShoppingCartFeature : NavTarget(ModuleRoutes.ShoppingCartFeature.label)
}

// Screen Targets
enum class ModuleRoutes(val label: String) {
    RootModule("rootmodule"),
    SecondModule("secondmodule"),
    ThirdModule("thirdmodule"),
    ShoppingCartModule("shoppingcartmodule"),
    Home("home"),
    Detail("detail"),
    SecondFeature("secondfeature"),
    ThirdFeature("thirdfeature"),
    ShoppingCartFeature("shoppingcartfeature"),
}