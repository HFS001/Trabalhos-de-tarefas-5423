package pt.tpsi_5423.guessthenumber

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import pt.tpsi_5423.guessthenumber.navigation.NavGraph
import pt.tpsi_5423.guessthenumber.ui.theme.GuessTheNumberTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GuessTheNumberTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    NavGraph()
                }
            }
        }
    }
}