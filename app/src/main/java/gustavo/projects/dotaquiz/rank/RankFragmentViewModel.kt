package gustavo.projects.dotaquiz.rank

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import gustavo.projects.dotaquiz.model.TeamInfo
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

    private fun formatTopPlayerScoreToString(topPlayersScore: List<TeamInfo>) : MutableList<String>{
        var listFormated = mutableListOf<String>()

        for(player in topPlayersScore){
            listFormated.add(player.teamName.toString() + " ----> " + player.teamBestScore.toString())
        }

        return listFormated
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
