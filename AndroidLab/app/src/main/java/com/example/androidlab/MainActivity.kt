package com.example.androidlab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LottoApp()
        }
    }
}

// 앱 전체를 감싸는 Composable
@Composable
fun LottoApp() {
    val navController = rememberNavController()

    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            LottoNavHost(navController = navController)
        }
    }
}

// 두 화면을 관리하는 NavHost
@Composable
fun LottoNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main") {
            MainScreen(
                onStartClick = { navController.navigate("result") }
            )
        }
        composable("result") {
            ResultScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}

// ----------- 메인 화면 -----------
@Composable
fun MainScreen(onStartClick: () -> Unit) {

}

// ----------- 결과 화면 -----------
@Composable
fun ResultScreen(onBackClick: () -> Boolean) {

}
