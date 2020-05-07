package gustavo.projects.dotaquiz.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class GameFragmentViewModel : ViewModel() {

    companion object{
        private const val TIMER_DONE = 0L
        private const val ONE_SECOND = 1000L
        private const val COUNTDOWN_TIME = 60000L
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

    init{
        _heroSelected.value = ""

        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND){
            override fun onTick(millisUntilFinished: Long) {
                _currentTimer.value = millisUntilFinished/ONE_SECOND
            }

            override fun onFinish() {
                _currentTimer.value = TIMER_DONE
                // END GAME IMPLEMENTATION ...
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

    //The HeroesMap stores each Hero name as a key, and the forbidden words related to that hero as values. The map should not be changed during game
    private fun createHeroesMap(){
        heroesMap = hashMapOf()
        heroesMap["Sniper"] = arrayOf("Headshot", "Dwarf", "Gun", "Shooter")
        heroesMap["Drow Ranger"] = arrayOf("Ice", "Archer", "Slow", "Aura")
        heroesMap["Gyrocopter"] = arrayOf("Helicopter", "Missile", "Stun", "Explosion")
        heroesMap["Lina"] = arrayOf("Fire", "Hot", "Lightning", "Stun")
        heroesMap["Storm Spirit"] = arrayOf("Lightning", "Blue", "Storm", "Mana")
        heroesMap["Antimage"] = arrayOf("Mage", "Blade", "Blink", "Resistant")
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
        }
    }

    private fun updateForbiddenWordsList(){
        if(heroesMap.containsKey(_heroSelected.value)){
            _forbiddenWordsList.value = heroesMap[_heroSelected.value]
        }
    }

    fun onCorrect(){
        nextHero()
    }
}