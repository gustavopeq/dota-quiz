package gustavo.projects.dotaquiz.pregame.teamSelection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import gustavo.projects.dotaquiz.model.RankDatabaseDao

class TeamSelectionViewModelFactory(private val database: RankDatabaseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TeamSelectionViewModel::class.java)){
            return  TeamSelectionViewModel(database) as T
        }else{
            throw IllegalArgumentException("Unknown TeamSelectionViewModel Class")
        }
    }
}