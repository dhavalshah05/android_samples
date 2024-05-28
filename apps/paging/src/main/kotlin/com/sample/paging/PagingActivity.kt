package com.sample.paging

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fynd.nitrozen.theme.NitrozenTheme
import com.sample.paging.recipeDetail.RecipeDetailScreen
import com.sample.paging.recipes.RecipeViewModel
import com.sample.paging.recipes.RecipesScreen

class PagingActivity : AppCompatActivity() {

    private val viewModel by viewModels<RecipeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NitrozenTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "recipes",
                    route = "rootGraph"
                ) {
                    composable(route = "recipes") {
                        RecipesScreen(
                            viewModel = viewModel,
                            onRecipeClick = {
                                navController.navigate("recipeDetail")
                            }
                        )
                    }

                    composable(route = "recipeDetail") {
                        RecipeDetailScreen(
                            onBackClick = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}