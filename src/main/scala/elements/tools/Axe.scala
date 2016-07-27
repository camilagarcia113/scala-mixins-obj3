package elements.tools

import elements.{Fighter, Character}
import elements.rocks.RockBlock
import elements.trees.Tree

abstract class Axe(val owner: Character) {

  def attackDamage: Int

  def attack(character: Fighter) = character.healthLevel -= 10

  def hit(tree: Tree) = {
    tree.getHit(attackDamage)
  }

}

class CommonAxe(owner: Character) extends Axe(owner) with Common {
  override var weathering = 70

  override def attackDamage = 1

  override def pointsOfWeathering = weathering * 0.03

  override def attack(character: Fighter) = {
    super.attack(character)
    wearOut
  }
  override def hit(tree:Tree) = {
    val h = super.hit(tree)
    wearOut
    h
  }
}

class ReinforcedAxe(owner: Character) extends Axe(owner) with Reinforced {

  def pointsOfDamage : Int = 5

  def smash(rockBlock: RockBlock): Unit = {
    rockBlock.getHit(pointsOfDamage)
  }

  override def attackDamage = 2
}