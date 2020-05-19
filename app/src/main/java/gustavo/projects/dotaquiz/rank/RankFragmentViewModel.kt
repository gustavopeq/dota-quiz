package gustavo.projects.dotaquiz.rank

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import gustavo.projects.dotaquiz.model.PlayerScore
import gustavo.projects.dotaquiz.model.RankDatabaseDao
import kotlinx.coroutines.*

class RankFragmentViewModel(val database: RankDatabaseDao) : ViewModel() {

    companion object{
        private const val NUMBER_OF_TOP_PLAYERS = 5
    }

    private var viewModelJob = Job()
    private var uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val topPlayersScore = database.getTopN(NUMBER_OF_TOP_PLAYERS)
    val topPlayersScoreString = Transformations.map(topPlayersScore) { topPlayersScore ->
        formatTopPlayerScoreToString(topPlayersScore)
    }

    private fun formatTopPlayerScoreToString(topPlayersScore: List<PlayerScore>) : MutableList<String>{
        var listFormated = mutableListOf<String>()

        var count = 1
        for(player in topPlayersScore){
            listFormated.add(count.toString() + "- " + player.playerName.toString() + " ----> " + player.playerBestScore.toString())
            count++
        }

        return listFormated
    }

    init {
        // Testing only
        uiScope.launch {
            //var newPlayer = PlayerScore(0,"Joao", 500)
            //insert(newPlayer)
        }
    }

    private suspend fun insert(newPlayerScore: PlayerScore){
        withContext(Dispatchers.IO){
            database.insert(newPlayerScore)
        }
    }

    // Testing only
    private suspend fun getPlayer(name: String) : PlayerScore{
        return withContext(Dispatchers.IO){
            var result = database.getByName(name)

            result
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
