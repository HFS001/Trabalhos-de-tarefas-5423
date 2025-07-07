
package pt.tpsi_5423.guessthenumber.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun WelcomeScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Bem-vindo ao Guess the Number!", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nome do Jogador") }
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { if (name.isNotBlank()) navController.navigate("game/$name") }) {
            Text("Novo Jogo")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = { navController.navigate("ranking") }) {
            Text("Ranking")
        }
    }
}