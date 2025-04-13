package com.rushikesh31apk.pokedex.ui_layer.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rushikesh31apk.pokedex.ui_layer.Screens.PokemonDetailsScreen
import com.rushikesh31apk.pokedex.ui_layer.Screens.PokemonListScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Pokemon_ListScreen") {
        composable("Pokemon_ListScreen"){
            //define function for Pokemon_ListScreen
           PokemonListScreen(navController = navController)
        }
        composable(
            "Pokemon_DetailScreen",
            arguments = listOf(
                navArgument("dominantColor") {
                    type = NavType.IntType
                },
                navArgument("pokemonName") {
                    type = NavType.StringType
                }
            )
            ){
            val dominantColor = remember {
                val color = it.arguments?.getInt("dominantColor")
                color?.let { Color(it) ?: Color.White }
            }
            val pokemonName = remember {
                it.arguments?.getString("pokemonName")
            }
            // define function for Pokemon_DetailScreen
            PokemonDetailsScreen(
                dominantColor = dominantColor,
                pokemonName = pokemonName?.lowercase() ?: "",
                navController = navController
            )

        }
    }

}