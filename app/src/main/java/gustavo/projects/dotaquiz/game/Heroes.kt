package gustavo.projects.dotaquiz.game

class Heroes {

    private val heroesMap: HashMap<String, Array<String>> = hashMapOf()

   //The HeroesMap stores each Hero name as a key, and the forbidden words related to that hero as values. The map should not be changed during game
     fun createHeroesMap() : HashMap<String, Array<String>> {
      heroesMap["Sniper"] = arrayOf("Headshot", "Dwarf", "Gun", "Shooter")
      heroesMap["Drow Ranger"] = arrayOf("Ice", "Archer", "Slow", "Aura")
      heroesMap["Gyrocopter"] = arrayOf("Helicopter", "Missile", "Stun", "Explosion")
      heroesMap["Lina"] = arrayOf("Fire", "Hot", "Lightning", "Stun")
      heroesMap["Storm Spirit"] = arrayOf("Lightning", "Blue", "Storm", "Mana")
      heroesMap["Antimage"] = arrayOf("Mage", "Blade", "Blink", "Resistant")
      heroesMap["Tiny"] = arrayOf("Rock", "Stone", "Tree", "Throw")
      heroesMap["IO"] = arrayOf("Link", "Regeneration", "Round", "Blue")
      heroesMap["Tusk"] = arrayOf("Snowball", "Punch", "Roll", "Ice")
      heroesMap["Pudge"] = arrayOf("Hook", "Fat", "Meat", "Stink")
      heroesMap["Magnus"] = arrayOf("Together", "Shock", "Cleave", "Tusk")
      heroesMap["Mirana"] = arrayOf("Moon", "Arrow", "Tiger", "Stars")
      heroesMap["Bounty Hunter"] = arrayOf("Shuriken", "Invisible", "Critical", "Money")
      heroesMap["Zeus"] = arrayOf("God", "Lightning", "Thunder", "Heaven")
      heroesMap["Invoker"] = arrayOf("Magic", "Sun", "Orbs", "Tornado")
      heroesMap["Nature's Prophet"] = arrayOf("Trees", "Teleport", "Rat", "Split")
      heroesMap["Silencer"] = arrayOf("Silence", "Mute", "Dispell", "Curse")
      heroesMap["Omniknight"] = arrayOf("Heal", "Knight", "Guardian", "Repel")

      return heroesMap
    }
}