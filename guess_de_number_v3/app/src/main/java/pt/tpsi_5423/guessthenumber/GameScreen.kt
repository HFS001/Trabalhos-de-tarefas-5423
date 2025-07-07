package pt.tpsi_5423.guessthenumber.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pt.tpsi_5423.guessthenumber.model.GameResult
import pt.tpsi_5423.guessthenumber.viewmodel.GameViewModel

@Composable
fun GameScreen(
    playerName: String,
    navController: NavController,
    viewModel: GameViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val secretNumber = remember { (1..100).random() }
    var guess by remember { mutableStateOf("") }
    var feedback by remember { mutableStateOf("") }
    var attempts by remember { mutableStateOf(0) }
    var finished by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(24.dp)) {
        Text("Adivinhe o número entre 1 e 100", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(16.dp))

        TextField(value = guess, onValueChange = { guess = it }, label = { Text("Palpite") })
        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = {
            val g = guess.toIntOrNull()
            if (g != null) {
                attempts++
                when {
                    g < secretNumber -> feedback = "Mais alto"
                    g > secretNumber -> feedback = "Mais baixo"
                    else -> {
                        feedback = "Acertou em $attempts tentativas!"
                        finished = true
                        viewModel.addResult(GameResult(playerName, attempts))
                    }
                }
            } else {
                feedback = "Digite um número válido"
            }
        }, enabled = !finished) {
            Text("Verificar")
        }

        Spacer(modifier = Modifier.height(10.dp))
        Text(feedback)

        if (finished) {
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = { navController.popBackStack() }) {
                Text("Voltar")
            }
        }
    }
}
