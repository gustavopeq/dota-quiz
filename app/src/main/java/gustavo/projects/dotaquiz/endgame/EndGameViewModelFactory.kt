package gustavo.projects.dotaquiz.endgame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import gustavo.projects.dotaquiz.model.RankDatabaseDao

@Suppress("UNCHECKED_CAST")
class EndGameViewModelFactory(private val finalScore: Int, private val teamName: String, private val dataSource: RankDatabaseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(EndGameViewModel::class.java)){
            return EndGameViewModel(finalScore, teamName, dataSource) as T
        }else{
            throw IllegalArgumentException("Unknown View Model Class")
        }
    }
}