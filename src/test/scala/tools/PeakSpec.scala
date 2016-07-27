package tools

import elements.Character
import elements.rocks.RockBlock
import elements.tools.{ReinforcedPeak, IronPeak}
import org.scalatest.{Matchers, FlatSpec}

class PeakSpec extends FlatSpec with Matchers {
  val arthur: Character = new Character

  "A peak" should "know its owner" in {
    val commonPeak: IronPeak = new IronPeak(arthur)
    val reinforcedPeak: ReinforcedPeak = new ReinforcedPeak(arthur)
    commonPeak.owner shouldBe arthur
    reinforcedPeak.owner shouldBe arthur
  }

  "An iron peak" should "have 70 points of weathering when instantiated" in {
    val commonPeak: IronPeak = new IronPeak(arthur)
    commonPeak.weathering shouldBe 70
  }

  "An iron peak" should "get damaged by 1 point when used" in {
    val commonPeak: IronPeak = new IronPeak(arthur)
    val rockBlock : RockBlock = new RockBlock(36)
    commonPeak.smash(rockBlock)
    commonPeak.weathering shouldBe 69
  }

  "An iron peak" should "be able to hit and damage a rock block" in {
    val commonPeak: IronPeak = new IronPeak(arthur)
    val rockBlock : RockBlock = new RockBlock(36)
    commonPeak.smash(rockBlock)
    commonPeak.weathering shouldBe 69
    rockBlock.resistance shouldBe 30
  }
  
  "A reinforced peak" should "be able to hit and damage a rock block" in {
    val reinforcedPeak: ReinforcedPeak = new ReinforcedPeak(arthur)
    val rockBlock : RockBlock = new RockBlock(36)
    reinforcedPeak.smash(rockBlock)
    rockBlock.resistance shouldBe 26
  }

  "A reinforced peak" should "cause a damage of 7 points when attacking" in {
    val axe: ReinforcedPeak = new ReinforcedPeak(arthur)
    val richard: Character = new Character
    axe.attack(richard)
    richard.healthLevel shouldBe 93
  }

  "An iron peak" should "cause a damage of 7 points and wear out when attacking" in {
    val axe: IronPeak = new IronPeak(arthur)
    val richard: Character = new Character
    axe.attack(richard)
    richard.healthLevel shouldBe 93
    axe.weathering shouldBe 69
  }
}
