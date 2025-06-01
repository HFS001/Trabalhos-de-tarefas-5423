package TPSI.jogo_guess_number

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import TPSI.jogo_guess_number.ui.theme.Jogo_guess_numberTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GuessTheNumberGame()
        }
    }
}

@Composable
fun GuessTheNumberGame() {
    var secretNumber by remember { mutableStateOf(Random.nextInt(1, 10)) }
    var guess by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("Tente adivinhar o número (1-10)") }
    var gameOver by remember { mutableStateOf(false) }
    var attempts by remember { mutableStateOf(0) }
    var ranking by remember { mutableStateOf(listOf<Int>()) }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Guess The Number", fontSize = 30.sp)
        TextField(
            value = guess,
            onValueChange = { guess = it },
            label = { Text("Sua tentativa") },
            singleLine = true
        )

        Button(onClick = {
            val num = guess.toIntOrNull()
            if (num == null) {
                message = "Por favor, insira um número válido!"
            } else {
                attempts++
                when {
                    num == 1234 -> message = "Dica: o número secreto é $secretNumber"
                    num < secretNumber -> message = "Tente um número maior!"
                    num > secretNumber -> message = "Tente um número menor!"
                    else -> {
                        message = "Parabéns! Acertou em $attempts tentativas!"
                        gameOver = true
                        ranking = (ranking + attempts).sorted()
                    }
                }
            }
        }, enabled = !gameOver) {
            Text("Verificar")
        }

        Text(text = message, fontSize = 18.sp)

        if (gameOver) {
            Button(onClick = {
                secretNumber = Random.nextInt(1, 10)
                guess = ""
                message = "Novo jogo! Tente adivinhar o número (1-10)"
                gameOver = false
                attempts = 0
            }) {
                Text("Jogar Novamente")
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "🏆 Ranking de Jogos (menos tentativas primeiro):", fontSize = 18.sp)
            ranking.forEachIndexed { index, tries ->
                Text(text = "${index + 1}º lugar: $tries tentativas", fontSize = 16.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Jogo_guess_numberTheme {
    }
}