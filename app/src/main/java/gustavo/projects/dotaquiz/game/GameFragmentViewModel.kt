package gustavo.projects.dotaquiz.game

import androidx.lifecycle.ViewModel

class GameFragmentViewModel : ViewModel() {

    private lateinit var heroesMap: HashMap<String, Array<String>>
    private  lateinit var heroesList: MutableSet<String>

    lateinit var heroSelected: String

    init{
        TODO("Create view model, starting on heroes hashmap")

        createHeroesMap()

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

            heroSelected = heroesList.elementAt(0)
            heroesList.remove(0)
        }
    }
}