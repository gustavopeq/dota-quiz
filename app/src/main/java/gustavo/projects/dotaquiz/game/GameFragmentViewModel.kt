package gustavo.projects.dotaquiz.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameFragmentViewModel : ViewModel() {

    private lateinit var heroesMap: HashMap<String, Array<String>>
    private var heroesList = mutableListOf<String>()

    private val _heroSelected = MutableLiveData<String>()
    val heroSelected: LiveData<String>
        get() = _heroSelected

    init{
        _heroSelected.value = ""

        createHeroesMap()
        createHeroesList()
        nextHero()

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

    private  fun nextHero(){
        if(heroesList.isNotEmpty()){
            _heroSelected.value = heroesList.removeAt(0)
        }
    }

    fun onCorrect(){
        nextHero()
    }
}