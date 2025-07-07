package pt.tpsi_5423.guessthenumber.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import pt.tpsi_5423.guessthenumber.model.GameResult

class GameViewModel : ViewModel() {

    val gameResults = mutableStateListOf<GameResult>()

    fun addResult(result: GameResult) {
        gameResults.add(result)
        gameResults.sortBy { it.attempts }
    }
}
