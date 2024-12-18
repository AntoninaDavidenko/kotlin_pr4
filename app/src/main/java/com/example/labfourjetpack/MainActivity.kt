package com.example.labfourjetpack

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import com.example.labfourjetpack.calculator1.Calculator1
import com.example.labfourjetpack.calculator2.Calculator2
import com.example.labfourjetpack.calculator3.Calculator3
import com.example.labfourjetpack.ui.EntryScreen

enum class Routes {
    MAIN_SCREEN,
    CALCULATOR_1,
    CALCULATOR_2,
    CALCULATOR_3
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = Routes.MAIN_SCREEN.name,
                    modifier = Modifier.padding(innerPadding)
                ) {
                    composable(route = Routes.MAIN_SCREEN.name) {
                        EntryScreen(
                            onCalculator1Navigate = { navController.navigate(route = Routes.CALCULATOR_1.name) },
                            onCalculator2Navigate = { navController.navigate(route = Routes.CALCULATOR_2.name) },
                            onCalculator3Navigate = { navController.navigate(route = Routes.CALCULATOR_3.name) },
                        )
                    }
                    composable(route = Routes.CALCULATOR_1.name) {
                        Calculator1(
                            goBack = { navController.navigate(route = Routes.MAIN_SCREEN.name) }
                        )
                    }
                    composable(route = Routes.CALCULATOR_2.name) {
                        Calculator2(
                            goBack = { navController.navigate(route = Routes.MAIN_SCREEN.name) }
                        )
                    }
                    composable(route = Routes.CALCULATOR_3.name) {
                        Calculator3(
                            goBack = { navController.navigate(route = Routes.MAIN_SCREEN.name) }
                        )
                    }
                }
            }
        }
    }
}