package pt.tpsi_5423.guessthenumber

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pt.tpsi_5423.guessthenumber.viewmodel.GameViewModel
import pt.tpsi_5423.guessthenumber.model.GameResult

@Composable
fun RankingScreen(
    navController: NavController,
    viewModel: GameViewModel
) {
    Column(modifier = Modifier.padding(24.dp)) {
        Text("Ranking dos Melhores Jogos", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(viewModel.gameResults) { result: GameResult ->
                Text("${result.playerName}: ${result.attempts} tentativas")
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Voltar")
        }
    }
}
