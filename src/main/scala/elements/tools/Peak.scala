package elements.tools

import elements.{Fighter, Character}
import elements.rocks.RockBlock

abstract class Peak(val owner: Character) {

  def attackDamage: Int

  def attack(character: Fighter) = character.healthLevel -= 7

  def smash(rockBlock: RockBlock) = {
    rockBlock.getHit(attackDamage)
  }

}

class IronPeak(owner: Character) extends Peak(owner) with Common with Iron {
           val resistance = 70
  override var weathering: Int = resistance

  override def attackDamage: Int = 6 * weathering / resistance

  override def pointsOfWeathering: Double = 1
  
  override def smash(rockBlock: RockBlock) = {
    val res = super.smash(rockBlock)
    wearOut
    res
  }

  override def attack(character: Fighter) = {
    super.attack(character)
    wearOut
  }

}

class ReinforcedPeak(owner: Character) extends Peak(owner) with Reinforced {

  override def attackDamage : Int = 10
}
