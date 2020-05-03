package gustavo.projects.dotaquiz.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameFragmentViewModel : ViewModel() {

    private lateinit var heroesMap: HashMap<String, Array<String>>
    private var heroesList: MutableSet<String>

    private val _heroSelected = MutableLiveData<String>()
    val heroSelected: LiveData<String>
        get() = _heroSelected

    init{
        _heroSelected.value = ""

        createHeroesMap()
        nextHero()

        heroesList = heroesMap.keys
    }

    private fun createHeroesMap(){
        heroesMap["Sniper"] = arrayOf("Headshot", "Dwarf", "Gun", "Shooter")
        heroesMap["Drow Ranger"] = arrayOf("Ice", "Archer", "Slow", "Aura")
        heroesMap["Gyrocopter"] = arrayOf("Helicopter", "Missile", "Stun", "Explosion")
        heroesMap["Lina"] = arrayOf("Fire", "Hot", "Lightning", "Stun")
        heroesMap["Storm Spirit"] = arrayOf("Lightning", "Blue", "Storm", "Mana")
        heroesMap["Antimage"] = arrayOf("Mage", "Blade", "Blink", "Resistant")
    }

    private  fun nextHero(){
        if(heroesList.isNotEmpty()){
            heroesList.shuffled()

            _heroSelected.value = heroesList.elementAt(0)
            heroesList.remove(_heroSelected.toString())
        }
    }

    fun onCorrect(){
        nextHero()
    }
}