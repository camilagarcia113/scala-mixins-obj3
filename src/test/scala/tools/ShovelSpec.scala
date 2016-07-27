package tools

import elements.tools.{ReinforcedShovel, CommonShovel}
import elements.{Grass, Character}
import land.Position
import org.scalatest.{Matchers, FlatSpec}

class ShovelSpec extends FlatSpec with Matchers {
  val arthur: Character = new Character

  "A shovel" should "know its owner" in {
    val commonShovel: CommonShovel = new CommonShovel(arthur)
    val reinforcedShovel: ReinforcedShovel = new ReinforcedShovel(arthur)
    commonShovel.owner shouldBe arthur
    reinforcedShovel.owner shouldBe arthur
  }

  "A common shovel" should "have 90 points of weathering when instantiated" in {
    val commonShovel: CommonShovel = new CommonShovel(arthur)
    commonShovel.weathering shouldBe 90
  }

  "A common shovel" should "get damaged by 3 points when it is used" in {
    val commonShovel: CommonShovel = new CommonShovel(arthur)
    commonShovel.dig(new Position(1,1))
    commonShovel.weathering shouldBe 87
  }

  "A common shovel" should "be able to dig up a plant" in {
    val commonShovel: CommonShovel = new CommonShovel(arthur)
    val position = new Position(1,1)
    commonShovel.dig(position)
    commonShovel.weathering shouldBe 87
    assert(position.isEmpty)
  }

  "A reinforced shovel" should "be able to dig up a plant" in {
    val reinforcedShovel: ReinforcedShovel = new ReinforcedShovel(arthur)
    val position = new Position(1,1)
    reinforcedShovel.dig(position)
    assert(position.isEmpty)
  }

  "A common shovel" should "be able to plant grass in a position" in {
    val commonShovel: CommonShovel = new CommonShovel(arthur)
    val position = new Position(3,4)
    commonShovel.plant(position, new Grass)
    commonShovel.weathering shouldBe 87
    assert(position.content.isInstanceOf[Grass])
  }

  "A reinforced shovel" should "be able to plant grass in a position" in {
    val reinforcedShovel: ReinforcedShovel = new ReinforcedShovel(arthur)
    val position = new Position(3,4)
    reinforcedShovel.plant(position, new Grass)
    assert(position.content.isInstanceOf[Grass])
  }

}
