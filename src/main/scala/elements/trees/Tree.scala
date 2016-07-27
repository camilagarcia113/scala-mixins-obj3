package elements.trees

import elements.{CustomRandom, Element, Wood}

abstract class Tree(resistance: CustomRandom) extends Element {

  var health: Int

  def getHit(damagePoints: Int): Array[Wood]

  def subtractHealthPoints(damagePoints: Int): Unit = {
    health -= damagePoints
  }

}
