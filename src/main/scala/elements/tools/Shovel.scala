package elements.tools

import elements.{Grass, Character}
import land.Position

abstract class Shovel(val owner: Character) {

  def dig(position: Position) = {
    position.removeElement
  }

  def plant(position: Position, grass: Grass) = {
    position.placeElement(grass)
  }

}

class CommonShovel(owner: Character) extends Shovel(owner) with Common {
  override var weathering: Int = 90

  override def pointsOfWeathering: Double = 3

  override def dig(position: Position) = {
    super.dig(position)
    wearOut
  }

  override def plant(position: Position, grass: Grass) = {
    val res = super.plant(position, grass)
    wearOut
    res
  }
}

class ReinforcedShovel(owner: Character) extends Shovel(owner) with Reinforced {
  // TODO dig up tree roots
}
