package elements

import elements.berries.{Cooked, BlueBerry, BlackBerry, Berry}
import elements.pigs._
import elements.rocks.Rock
import org.scalatest.{FlatSpec, Matchers}

import scala.collection.mutable.ListBuffer

class CharacterSpec extends FlatSpec with Matchers {

  "A new character" should "have 100 health points" in {
    val arthur = new Character
    arthur.healthLevel should be (100)
  }

  "A character" should "be able to receive damage" in {
    val arthur = new Character
    arthur.getDamagedBy(30, null)
    arthur.healthLevel should be (70)
  }

  "When a character meets a happy pig it" should "have a new berry in his stock" in {
    val arthur = new Character
    var stock: ListBuffer[Berry] = new ListBuffer[Berry]()
    stock += new BlueBerry
    stock += new BlackBerry(3)
    val happyPig = new Pig(stock, new Happy)
    happyPig.meetCharacter(arthur)
    arthur.trunk.length should be (1)
  }

  "When a character meets a neutral pig it" should "not have a new berry in his stock" in {
    val arthur = new Character
    var stock: ListBuffer[Berry] = new ListBuffer[Berry]()
    stock += new BlueBerry
    stock += new BlackBerry(3)
    val neutralPig = new Pig(stock, new Neutral)
    neutralPig.meetCharacter(arthur)
    arthur.trunk.length should be (0)
  }

  "When a character meets a bad pig it" should "not have a new berry in his stock" in {
    val arthur = new Character
    var stock: ListBuffer[Berry] = new ListBuffer[Berry]()
    stock += new BlueBerry
    stock += new BlackBerry(3)
    val badPig = new Pig(stock, new Bad(new CustomRandom))
    badPig.meetCharacter(arthur)
    arthur.trunk.length should be (0)
  }

  "A character" should "be able to build a campfire when he has all the necessary items" in {
    val arthur = new Character
    arthur.collectStone(new Rock)
    arthur.collectStone(new Rock)
    arthur.collectGrass(new CutGrass)
    arthur.collectGrass(new CutGrass)
    arthur.collectGrass(new CutGrass)
    assert(arthur.canBuildACampFire)
  }

  "A character" should "not be able to build a campfire when he hasn't got all the necessary items" in {
    val arthur = new Character
    arthur.collectStone(new Rock)
    arthur.collectStone(new Rock)
    arthur.collectGrass(new CutGrass)
    arthur.collectGrass(new CutGrass)
    arthur.canBuildACampFire should be (false)
  }

  "A character" should "be able to build a campfire" in {
    val arthur = new Character
    arthur.collectStone(new Rock)
    arthur.collectStone(new Rock)
    arthur.collectGrass(new CutGrass)
    arthur.collectGrass(new CutGrass)
    arthur.collectGrass(new CutGrass)

    val initialSize: Int = arthur.trunk.length
    arthur.buildACampfire()
    arthur.trunk.head shouldBe a [Campfire]
    arthur.trunk.length shouldBe initialSize - 4 // 5 elements deleted but added a campfire
  }

  "A character with minimum health" should "be damaged by not less than its minimum" in {
    val arthur = new Character with MinimumHealth
    arthur.getDamagedBy(10, new Character)
    arthur.healthLevel shouldBe 90
    arthur.getDamagedBy(90, new Character)
    arthur.healthLevel shouldBe 10
  }

  "A character with resistant health" should "be damaged by a third of the attack points" in {
    val arthur = new Character with ResistantHealth
    arthur.getDamagedBy(30, new Character)
    arthur.healthLevel shouldBe 90
  }

  "A character with damage rebound health" should "be damaged and hurt his attacker by half the attack points" in {
    val arthur = new Character with DamageReboundHealth
    val badGuy = new Character
    arthur.getDamagedBy(30, badGuy)
    arthur.healthLevel shouldBe 70
    badGuy.healthLevel shouldBe 85
  }

//  "Two characters with damage rebound health" should "attack each other only once" in {
//    val arthur = new Character with DamageReboundHealth
//    val badGuy = new Character with DamageReboundHealth
//    arthur.getDamagedBy(30, badGuy)
//    arthur.healthLevel shouldBe 70
//    badGuy.healthLevel shouldBe 85
//  }

  "A character with super efficient metabolism" should "receive twice the food's energy" in {
    val arthur = new Character with SuperEfficientMetabolism
    val blueBerry = new BlueBerry with Cooked
    val rottenBlueBerry = new BlueBerry with Cooked
    blueBerry.getEatenBy(arthur)
    arthur.healthLevel shouldBe 144 // (20 + 2) * 2
    rottenBlueBerry.getEatenBy(arthur)
    arthur.healthLevel shouldBe 188 // -(20 + 2) * 2
  }

  "A character with defficient metabolism" should "receive a smaller amount of energy" in {
    val arthur = new Character with DefficientMetabolism {
      override val percentage: Int = 50
    }
    val blueBerry = new BlueBerry
    blueBerry.getEatenBy(arthur)
    arthur.healthLevel shouldBe 110
  }

  "A character with limited metabolism" should "have an energy limit" in {
    val arthur = new Character with LimitedMetabolism {
      override val limit: Int = 110
    }
    val blueBerry = new BlueBerry
    blueBerry.getEatenBy(arthur)
    arthur.healthLevel shouldBe 110
  }
}
