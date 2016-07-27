package elements.berries

import elements.Character

trait Cooked extends Berry{

  override def getEatenBy(character: Character): Unit = {
    character.getBetterBy((pointsOfEnergy * 1.1).toInt)
  }
}

trait Rotten extends Berry{

  override def getEatenBy(character: Character): Unit = {
    character.getBetterBy(-(pointsOfEnergy / 3))
  }
}