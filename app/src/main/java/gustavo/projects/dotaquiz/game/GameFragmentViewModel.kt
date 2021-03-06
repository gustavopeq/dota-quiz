package gustavo.projects.dotaquiz.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class GameFragmentViewModel(teamNameArg: String) : ViewModel() {

    companion object{
        private const val TIMER_DONE = 0L
        private const val ONE_SECOND = 1000L
        private const val COUNTDOWN_TIME = 60000L

        private const val BASE_SCORE_POINTS = 10
    }

    private lateinit var heroesMap: HashMap<String, Array<String>>
    private var heroesList = mutableListOf<String>()

    private val _heroSelected = MutableLiveData<String>()
    val heroSelected: LiveData<String>
        get() = _heroSelected

    private val _forbiddenWordsList = MutableLiveData<Array<String>>()
    val forbiddenWordsList: LiveData<Array<String>>
        get() = _forbiddenWordsList

    private val _currentTimer = MutableLiveData<Long>()
    val currentTimer: LiveData<Long>
        get() = _currentTimer

    val currentTimerString = Transformations.map(currentTimer) { time ->
        DateUtils.formatElapsedTime(time)
    }

    private val timer: CountDownTimer

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish

    private val _listOfHeroesEmpty = MutableLiveData<Boolean>()
    val listOfHeroesEmpty: LiveData<Boolean>
        get() = _listOfHeroesEmpty

    private val _teamName = MutableLiveData<String>()
    val teamName: LiveData<String>
        get() = _teamName

    init{
        _heroSelected.value = ""
        _score.value = 0
        _teamName.value = teamNameArg
        _eventGameFinish.value = false

        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND){
            override fun onTick(millisUntilFinished: Long) {
                _currentTimer.value = millisUntilFinished/ONE_SECOND
            }

            override fun onFinish() {
                _currentTimer.value = TIMER_DONE
                onGameFinish()
            }
        }

        timer.start()

        createHeroesMap()
        createHeroesList()
        nextHero()

    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }


    private fun createHeroesMap(){
        val heroes = Heroes()
        heroesMap = heroes.createHeroesMap()
    }

    //The HeroesList gets the name of each hero available, and is used during the game. Every time a hero is picked, it is removed from this list
    private fun createHeroesList(){
        heroesList = heroesMap.keys.toMutableList()
        heroesList.shuffle()
    }

    private fun nextHero(){
        if(heroesList.isNotEmpty()){
            _heroSelected.value = heroesList.removeAt(0)
            updateForbiddenWordsList()
        }else{
            _listOfHeroesEmpty.value = true
            onGameFinish()
        }
    }

    private fun updateForbiddenWordsList(){
        if(heroesMap.containsKey(_heroSelected.value)){
            _forbiddenWordsList.value = heroesMap[_heroSelected.value]
        }
    }

    private fun addPointsToScore(){
        val timerBonusMultiplier = _currentTimer.value!!
        _score.value = _score.value?.plus((BASE_SCORE_POINTS * timerBonusMultiplier.toInt()))
    }

    private fun onGameFinish(){
        _eventGameFinish.value = true
    }

    // This method is important in order to avoid the onGameFinish runs again if there is any configuration change.
    fun onGameFinishComplete(){
        _eventGameFinish.value = false
    }

    fun onCorrect(){
        addPointsToScore()
        nextHero()
    }

    fun onWrong(){
        nextHero()
    }



}