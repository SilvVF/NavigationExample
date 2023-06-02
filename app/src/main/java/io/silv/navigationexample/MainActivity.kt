package io.silv.navigationexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.internal.composableLambdaN
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.silv.navigationexample.nav.AppComposeNavigator
import io.silv.navigationexample.nav.SampleScreens
import io.silv.navigationexample.ui.theme.NavigationExampleTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val  composeNavigator by inject<AppComposeNavigator>()
        
        setContent {

            val vm = koinViewModel<SampleViewModel>()

            LaunchedEffect(Unit) {
                vm.start()
            }

           SampleMainNavigator(composeNavigator = composeNavigator)
        }
    }
}

class SampleViewModel(
    private val appComposeNavigator: AppComposeNavigator
):  ViewModel() {

    fun start () {
        viewModelScope.launch {
            while (true) {
                delay(5000)
                appComposeNavigator.navigate(
                    listOf(SampleScreens.Home.route, SampleScreens.Login.route).random()
                )
            }
        }
    }
}

@Composable
fun SampleMainNavigator(
    composeNavigator: AppComposeNavigator
) {
    NavigationExampleTheme() {
        val navHostController = rememberNavController()

        LaunchedEffect(Unit) {
            composeNavigator.handleNavigationCommands(navHostController)
        }

        Surface {
            NavHost(
                navController = navHostController,
                startDestination = SampleScreens.Home.route
            ) {
                composable(
                    SampleScreens.Home.route
                ) {
                    Column(Modifier.fillMaxSize()) {
                        Text(text = "Home")
                        Button(onClick = { composeNavigator.navigate(SampleScreens.Login.route) }) {

                        }
                    }
                }
                composable(SampleScreens.Login.route) {
                    Column(Modifier.fillMaxSize()) {
                        Text(text = "Login")
                        Button(onClick = { composeNavigator.navigate(SampleScreens.Home.route) }) {

                        }
                    }
                }
            }
        }
    }
}