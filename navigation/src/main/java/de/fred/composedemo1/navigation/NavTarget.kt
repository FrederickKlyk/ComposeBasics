package de.fred.composedemo1.navigation

sealed class NavTarget(val label: String) {
    object RootModule : NavTarget(ModuleRoutes.RootModule.label)

    sealed class SecondFeatureModule {
        data class SecondModuleWithParams(val id: String) : NavTarget("${ModuleRoutes.SecondModule}/$id")
        data class SecondFeatureWithParams(val id: String) : NavTarget("${ModuleRoutes.SecondFeature.label}/$id")
    }

    object ThirdModule : NavTarget(ModuleRoutes.ThirdModule.label)
    object Home : NavTarget(ModuleRoutes.Home.label)
    object Detail : NavTarget(ModuleRoutes.Detail.label)
    object ThirdFeature : NavTarget(ModuleRoutes.ThirdFeature.label)
}

// Screen Targets
enum class ModuleRoutes(val label: String) {
    RootModule("rootmodule"),
    SecondModule("secondmodule"),
    ThirdModule("thirdmodule"),
    Home("home"),
    Detail("detail"),
    SecondFeature("secondfeature"),
    ThirdFeature("thirdfeature")
}