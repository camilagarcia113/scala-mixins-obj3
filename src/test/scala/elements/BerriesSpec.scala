package elements

import elements.berries.{Rotten, Cooked, BlueBerry, BlackBerry}
import org.scalatest.{FlatSpec, Matchers}

class BerriesSpec extends FlatSpec with Matchers {

  "A blue berry" should "give 20 points of energy when eaten" in {
    val blueBerry = new BlueBerry
    val arthur = new Character
    blueBerry.getEatenBy(arthur)
    arthur.healthLevel shouldBe 120
  }

  "A black berry" should "give 10% of its height in energy points when eaten" in {
    val blackBerry = new BlackBerry(40)
    val arthur = new Character
    blackBerry.getEatenBy(arthur)
    arthur.healthLevel shouldBe 104
  }

  "A cooked berry" should "give 10% extra energy" in {
    val blueBerry = new BlueBerry with Cooked
    val blackBerry = new BlackBerry(400) with Cooked
    val arthur = new Character
    blueBerry.getEatenBy(arthur)
    arthur.healthLevel shouldBe 122 // 20 + 2
    blackBerry.getEatenBy(arthur)
    arthur.healthLevel shouldBe 166 // 40 + 4
  }

  "A rotten berry" should "take a third of its usual energy" in {
    val blueBerry = new BlueBerry with Rotten
    val blackBerry = new BlackBerry(999) with Rotten
    val arthur = new Character
    blueBerry.getEatenBy(arthur)
    arthur.healthLevel shouldBe 94 // 20 / 3
    blackBerry.getEatenBy(arthur)
    arthur.healthLevel shouldBe 61 // 99 / 3
  }


}
