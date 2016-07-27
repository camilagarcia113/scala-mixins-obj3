package tools

import elements.rocks.RockBlock
import elements.trees.SmallTree
import elements.{CustomRandom, Character}
import elements.tools.{ReinforcedPeak, ReinforcedAxe, CommonAxe}
import org.scalatest.{FlatSpec, Matchers}

class AxeSpec extends FlatSpec with Matchers {
  val arthur: Character = new Character

  object CustomSmallTree extends CustomRandom {
    override def smallTreeRandom(): Int = 3
  }

  "An axe" should "know its owner" in {
    val commonAxe: CommonAxe = new CommonAxe(arthur)
    val reinforcedAxe: ReinforcedAxe = new ReinforcedAxe(arthur)
    commonAxe.owner shouldBe arthur
    reinforcedAxe.owner shouldBe arthur
  }

  "A common axe" should "have 70 points of weathering when instantiated" in {
    val axe: CommonAxe = new CommonAxe(arthur)
    axe.weathering shouldBe 70
  }

  "A common axe" should "get damaged by 3% when it is used" in {
    val axe: CommonAxe = new CommonAxe(arthur)
    val tree: SmallTree = new SmallTree(new CustomRandom)
    axe.hit(tree)
    axe.weathering shouldBe 68
  }

  "A common axe" should "damage one time a tree when used against it" in {
    val axe: CommonAxe = new CommonAxe(arthur)
    val tree: SmallTree = new SmallTree(CustomSmallTree)
    axe.hit(tree)
    axe.weathering shouldBe 68
    tree.health shouldBe 2
  }

  "A reinforced axe" should "damage two times a tree when used against it" in {
    val axe: ReinforcedAxe = new ReinforcedAxe(arthur)
    val tree: SmallTree = new SmallTree(CustomSmallTree)
    axe.hit(tree)
    tree.health shouldBe 1
  }

  "A reinforced axe" should "be able to hit and damage a rock block" in {
    val axe: ReinforcedAxe = new ReinforcedAxe(arthur)
    val rockBlock : RockBlock = new RockBlock(31)
    axe.smash(rockBlock)
    rockBlock.resistance shouldBe 26
  }

  "A reinforced axe" should "cause a damage of 10 points when attacking" in {
    val axe: ReinforcedAxe = new ReinforcedAxe(arthur)
    val richard: Character = new Character
    axe.attack(richard)
    richard.healthLevel shouldBe 90
  }

  "A common axe" should "cause a damage of 10 points and wear out when attacking" in {
    val axe: CommonAxe = new CommonAxe(arthur)
    val richard: Character = new Character
    axe.attack(richard)
    richard.healthLevel shouldBe 90
    axe.weathering shouldBe 68
  }

}