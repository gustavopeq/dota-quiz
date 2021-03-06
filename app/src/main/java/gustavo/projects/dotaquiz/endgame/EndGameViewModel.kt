package gustavo.projects.dotaquiz.endgame

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import gustavo.projects.dotaquiz.model.RankDatabaseDao
import gustavo.projects.dotaquiz.model.TeamInfo
import kotlinx.coroutines.*

class EndGameViewModel(finalScoreArg: Int, teamNameArg: String, databaseArg: RankDatabaseDao) : ViewModel(){

    companion object {
        private const val CURRENT_FRAGMENT = 0
        private const val TITLE_FRAGMENT = 1
        private const val TUTORIAL_FRAGMENT = 2
        private const val RANKING_FRAGMENT = 3
    }

    private val viewModelJob= Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val database: RankDatabaseDao

    private val _finalScore = MutableLiveData<Int>()
    val finalScore: LiveData<Int>
        get() = _finalScore

    private val _teamName = MutableLiveData<String>()
    val teamName: LiveData<String>
        get() = _teamName

    private val _teamInfo = MutableLiveData<TeamInfo>()
    val teamInfoLiveData: LiveData<TeamInfo>
        get() = _teamInfo

    private val _endGameCompleted = MutableLiveData<Boolean>()
    val endGameCompleted: LiveData<Boolean>
        get() = _endGameCompleted

    // Destination ID defines the navigation route to be followed. 1 = TitleFragment / 2 = TutorialFragment
    private val _destinationID = MutableLiveData<Int>()
    val destinationID: LiveData<Int>
        get() = _destinationID

    init{
        _finalScore.value = finalScoreArg
        _teamName.value = teamNameArg
        database = databaseArg
        _endGameCompleted.value = false
    }

    fun onMainMenuBtnEvent() {
        _destinationID.value = TITLE_FRAGMENT
    }

    fun onPlayAgainBtnEvent() {
        _destinationID.value = TUTORIAL_FRAGMENT
    }

    fun onRankingBtnEvent() {
        _destinationID.value = RANKING_FRAGMENT
    }

    fun onDestinationChangeComplete(){
        _destinationID.value = CURRENT_FRAGMENT
    }

    fun onUpdateTeamInfo() {
        uiScope.launch {

            var teamInfo = getTeamInfoFromDatabase()

            if (teamInfo != null) {
                if (_finalScore.value!! > teamInfo.teamBestScore) {
                    teamInfo.teamBestScore = _finalScore.value!!

                    updateTeamInfoOnDatabase(teamInfo)
                }
            }else {
                teamInfo = TeamInfo(0,_teamName.value!!, _finalScore.value!!)
                insertTeamInfoIntoDatabase(teamInfo)
            }

            _teamInfo.value = teamInfo
            onEndGameCompleted()
        }
    }


    private suspend fun getTeamInfoFromDatabase() : TeamInfo? {
        return withContext(Dispatchers.IO) {
            return@withContext database.getByName(_teamName.value!!)
        }
    }

    private suspend fun insertTeamInfoIntoDatabase(teamInfo : TeamInfo) {
        withContext(Dispatchers.IO) {
            database.insert(teamInfo)
        }
    }

    private suspend fun updateTeamInfoOnDatabase(teamInfo : TeamInfo) {
        withContext(Dispatchers.IO) {
            database.update(teamInfo)
        }
    }

    // It will prevent the fragment to try to update the team info in case the user rotate the cellphone
    private fun onEndGameCompleted() {
        _endGameCompleted.value = true
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}