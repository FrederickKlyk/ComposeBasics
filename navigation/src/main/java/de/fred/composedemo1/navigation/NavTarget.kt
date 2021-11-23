package de.fred.composedemo1.navigation

sealed class NavTarget(val label: String) {
    object RootModule : NavTarget(ModuleRoutes.RootModule.label)
    sealed class SecondFeatureModule {
        object SecondFeature : NavTarget("${ModuleRoutes.SecondFeature.label}/{secondId}")
        data class SecondModuleWithParams(val secondId: String) : NavTarget("secondmodule/$secondId")
        data class SecondFeatureWithParams(val secondId: String) : NavTarget("${ModuleRoutes.SecondFeature.label}/$secondId")
    }

    object ThirdModule : NavTarget(ModuleRoutes.ThirdModule.label)
    object Home : NavTarget(ModuleRoutes.Home.label)
    object Detail : NavTarget(ModuleRoutes.Detail.label)
    object ThirdFeature : NavTarget(ModuleRoutes.ThirdFeature.label)

    sealed class Test {
        object TestClass : NavTarget("secondmodule/tersrwesa")
    }
}

// Screen Targets
enum class ModuleRoutes(val label: String) {
    RootModule("rootmodule"),
    SecondModule("secondmodule/{secondId}"),
    ThirdModule("thirdmodule"),
    Home("home"),
    Detail("detail"),
    SecondFeature("secondfeature"),
    ThirdFeature("thirdfeature")
}