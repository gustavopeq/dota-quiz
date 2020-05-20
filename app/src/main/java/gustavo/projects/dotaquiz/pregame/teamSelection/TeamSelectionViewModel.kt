package gustavo.projects.dotaquiz.pregame.teamSelection

import android.util.Log
import androidx.lifecycle.ViewModel
import gustavo.projects.dotaquiz.model.PlayerScore
import gustavo.projects.dotaquiz.model.RankDatabaseDao
import kotlinx.coroutines.*
import java.lang.Exception

class TeamSelectionViewModel(val database: RankDatabaseDao) : ViewModel() {


    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private lateinit var teamSelectedName: String

    fun createNewTeam(teamName: String) {
        uiScope.launch {
            if(insertTeam(teamName)) {
                teamSelectedName = teamName
            }else{
                Log.i("print", "Team already exist... Dialog message needs to popup now")
            }
        }
    }

    // True is returned confirming that the new team was inserted. If false is returned, means that this teamName already exists in the Database
    private suspend fun insertTeam(teamName: String) : Boolean{
        return withContext(Dispatchers.IO) {
            var newTeam = PlayerScore(0, teamName, 0)

            try {
                database.insert(newTeam)
            }catch (e: Exception){
                // Team already exist in the database
                Log.i("print", e.message)

                return@withContext false
            }

            true
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
