package gustavo.projects.dotaquiz.rank

import android.util.Log
import androidx.lifecycle.ViewModel
import gustavo.projects.dotaquiz.model.PlayerScore
import gustavo.projects.dotaquiz.model.RankDatabaseDao
import kotlinx.coroutines.*

class RankFragmentViewModel(val database: RankDatabaseDao) : ViewModel() {

    private var viewModelJob = Job()
    private var uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    init{
        uiScope.launch {

            var test = getAllPlayers()

            Log.i("print", test.size.toString())

            //val newPlayerScore = PlayerScore(0,"Gustavo", 1000)
            //insert(newPlayerScore)
        }


    }

    private suspend fun getAllPlayers(): Array<PlayerScore>{
        return withContext(Dispatchers.IO){
            var result = database.getTop5()

            return@withContext result
        }
    }

    private suspend fun insert(newPlayerScore: PlayerScore) {
        withContext(Dispatchers.IO){
            database.insert(newPlayerScore)


        }
    }

    fun checkDatabase(){
        uiScope.launch {

        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
