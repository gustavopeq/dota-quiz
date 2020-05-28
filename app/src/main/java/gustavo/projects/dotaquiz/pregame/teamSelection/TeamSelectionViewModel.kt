package gustavo.projects.dotaquiz.pregame.teamSelection

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import gustavo.projects.dotaquiz.model.TeamInfo
import gustavo.projects.dotaquiz.model.RankDatabaseDao
import kotlinx.coroutines.*
import java.lang.Exception

class TeamSelectionViewModel(private val database: RankDatabaseDao) : ViewModel() {


    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    lateinit var teamSelected: TeamInfo

    private val _canStartGame = MutableLiveData<Boolean>()
    val canStartGame: LiveData<Boolean>
        get() = _canStartGame

    private val _canShowExistentTeamDialog = MutableLiveData<Boolean>()
    val canShowExistentTeamDialog: LiveData<Boolean>
        get() = _canShowExistentTeamDialog

    fun createNewTeam(teamName: String) {
        uiScope.launch {
            insertTeam(teamName)
        }
    }

    private suspend fun insertTeam(teamName: String){
        withContext(Dispatchers.IO) {
            var newTeam = TeamInfo(0, teamName, 0)

            try {
                database.insert(newTeam)
                prepareToStartGame(newTeam)
            }catch (e: Exception){

                // Team already exist in the database
                Log.i("print", e.message!!)
                prepateToShowExistentTeamDialog(database.getByName(newTeam.teamName))
            }
        }
    }

    private fun prepareToStartGame(teamInfo: TeamInfo) {
        teamSelected = teamInfo
        _canStartGame.postValue(true)
    }

    private fun prepateToShowExistentTeamDialog(teamInfo: TeamInfo) {
        teamSelected = teamInfo
        _canShowExistentTeamDialog.postValue(true)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
