package gustavo.projects.dotaquiz.endgame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class EndGameViewModelFactory(private val finalScore: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(EndGameViewModel::class.java)){
            return EndGameViewModel(finalScore) as T
        }else{
            throw IllegalArgumentException("Unknown View Model Class")
        }
    }
}