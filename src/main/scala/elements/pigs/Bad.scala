package elements.pigs

import elements.{Fighter, CustomRandom, Character}
import elements.berries.{Berry, NoBerry}

class Bad(val randomMood: CustomRandom) extends Mood {

  override def receiveVisit(pig: Pig, character: Character) = damageDependingOnMood(character)

  def damageDependingOnMood (character: Character) {
    if(randomMood.wantsToHurt == 1) {
      damageCharacter(character)
    }
  }

  private def damageCharacter(character: Fighter): Unit ={
    character.getDamagedBy(10, null) // TODO refactor
  }
}