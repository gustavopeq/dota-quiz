package gustavo.projects.dotaquiz.endgame

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EndGameViewModel(finalScore: Int) : ViewModel(){

    private val _finalScore = MutableLiveData<Int>()
    val finalScore: LiveData<Int>
        get() = _finalScore

    // Destination ID defines the navigation route to be followed. 1 = TitleFragment / 2 = TutorialFragment
    private val _destinationID = MutableLiveData<Int>()
    val destinationID: LiveData<Int>
        get() = _destinationID

    init{
        _finalScore.value = finalScore
    }

    fun onMainMenuBtnEvent(){
        _destinationID.value = 1
    }

    fun onPlayAgainBtnEvent(){
        _destinationID.value = 2
    }

    fun onDestinationChangeComplete(){
        _destinationID.value = 0
    }
}