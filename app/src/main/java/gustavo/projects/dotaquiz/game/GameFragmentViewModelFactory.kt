package gustavo.projects.dotaquiz.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class GameFragmentViewModelFactory(private val teamName: String) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(GameFragmentViewModel::class.java)){
            return GameFragmentViewModel(teamName) as T
        }else{
            throw IllegalArgumentException("Unknown Game Fragment View Model Class")
        }
    }
}