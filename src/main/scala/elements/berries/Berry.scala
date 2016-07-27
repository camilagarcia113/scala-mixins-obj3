package elements.berries

import elements.{Character, Element}

abstract class Berry extends Element {
  val pointsOfEnergy: Int

  def getEatenBy(character: Character): Unit = {
    character.getBetterBy(pointsOfEnergy)
  }

}
