package app.bookpedia.com

import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator

object AppNavigator {

    var navArguments =  mutableMapOf<String, Map<String, Any>>()

    fun <T : Screen> push(navigator: Navigator?, screen: T){
        navigator?.push(screen)
    }

    fun <T : Screen> push(navigator: Navigator?, screen: T, arguments: Map<String, Any>){
        setArguments(screen::class.simpleName ?: "unknown", arguments)
        navigator?.push(screen)
    }

    fun pop(navigator: Navigator?){
        navigator?.pop()
    }

    fun <T : Screen> popUntil(navigator: Navigator?, screen: T){
        navigator?.popUntil { it == screen }
    }

    fun setArguments(key: String, arguments: Map<String, Any>){
        navArguments[key] = arguments
    }

    fun getArguments(screen: Screen) : Map<String, Any>{
        val screenKey = screen::class.simpleName ?: "unknown"
        return navArguments[screenKey] ?: emptyMap()
    }

    fun clearArguments(screen: Screen) {
        val screenKey = screen::class.simpleName ?: "unknown"
        navArguments.remove(screenKey)
    }

    fun clearAll() {
        navArguments.clear()
    }
}