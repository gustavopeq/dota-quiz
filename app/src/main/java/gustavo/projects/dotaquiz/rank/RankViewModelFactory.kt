package gustavo.projects.dotaquiz.rank

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import gustavo.projects.dotaquiz.model.RankDatabaseDao

class RankViewModelFactory(private val dataSource: RankDatabaseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RankFragmentViewModel::class.java)){
            return RankFragmentViewModel(dataSource) as T
        }else{
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}