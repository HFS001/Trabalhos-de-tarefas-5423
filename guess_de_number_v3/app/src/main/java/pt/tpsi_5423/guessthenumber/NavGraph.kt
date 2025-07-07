package pt.tpsi_5423.guessthenumber.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pt.tpsi_5423.guessthenumber.screens.GameScreen
import pt.tpsi_5423.guessthenumber.RankingScreen
import pt.tpsi_5423.guessthenumber.screens.WelcomeScreen
import pt.tpsi_5423.guessthenumber.viewmodel.GameViewModel

@Composable
fun NavGraph(navController: NavHostController = rememberNavController()) {
    val viewModel: GameViewModel = viewModel()

    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") {
            WelcomeScreen(navController)
        }
        composable("game/{playerName}") { backStackEntry ->
            val playerName = backStackEntry.arguments?.getString("playerName") ?: "Jogador"
            GameScreen(playerName, navController, viewModel)
        }
        composable("ranking") {
            RankingScreen(navController, viewModel)
        }
    }
}
