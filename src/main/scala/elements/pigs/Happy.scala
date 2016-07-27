package elements.pigs

import elements.Character

class Happy extends Mood {

  override def receiveVisit(pig: Pig, character: Character) = giveFoodTo(pig, character)

  def giveFoodTo(pig: Pig, character: Character) {
    character.addFood(pig.giveAwayFood)
    pig.removeFood
  }
}