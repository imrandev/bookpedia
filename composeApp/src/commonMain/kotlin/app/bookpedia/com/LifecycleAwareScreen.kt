package app.bookpedia.com

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import cafe.adriel.voyager.core.screen.Screen

interface ScreenLifecycle {
    fun onScreenAppeared()
    fun onScreenDisappeared()
}

abstract class LifecycleAwareScreen : Screen, ScreenLifecycle {

    @Composable
    override fun Content() {
        DisposableEffect(Unit) {
            onScreenAppeared()

            onDispose {
                onScreenDisappeared()
            }
        }

        ScreenContent()
    }

    @Composable
    abstract fun ScreenContent()

    override fun onScreenAppeared() {
        println("${this::class.simpleName} appeared")
        println(AppNavigator.getArguments(this).toString())
    }

    override fun onScreenDisappeared() {
        AppNavigator.clearArguments(this)
        println(AppNavigator.getArguments(this).toString())
        println("${this::class.simpleName} disappeared")
    }
}