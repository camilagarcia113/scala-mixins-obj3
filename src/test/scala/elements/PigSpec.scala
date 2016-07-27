package elements

import elements.berries.{BlackBerry, Berry, BlueBerry}
import elements.pigs._
import org.scalatest.{FlatSpec, Matchers}

import scala.collection.mutable.ListBuffer

class PigSpec extends FlatSpec with Matchers {

  "A happy pig" should "give the character food when visited at his home" in {
    val arthur = new Character
    var stock: ListBuffer[Berry] = new ListBuffer[Berry]()
    stock += new BlueBerry
    stock += new BlueBerry
    val happy = new Happy
    val happyPig = new Pig(stock, happy)
    val happyPigHouse = new PigHouse(happyPig)
    arthur.trunk.length shouldBe 0
    happyPigHouse.receiveVisitor(arthur)
    arthur.trunk.length shouldBe 1
  }

  "A neutral pig" should "not do anything when visited at his home" in {
    val arthur = new Character
    var stock: ListBuffer[Berry] = new ListBuffer[Berry]()
    stock += new BlueBerry
    stock += new BlueBerry
    val neutral = new Neutral
    val neutralPig = new Pig(stock, neutral)
    val neutralPigHouse = new PigHouse(neutralPig)
    arthur.trunk.length shouldBe 0
    neutralPigHouse.receiveVisitor(arthur)
    arthur.trunk.length shouldBe 0
    neutralPig.foodStock.length shouldBe 2
  }

  "A bad pig" should "damage the character by 10 points when the pig is mad" in {
    object AlwaysMad extends CustomRandom {
      override def wantsToHurt(): Int = 1
    }

    val arthur = new Character
    var stock: ListBuffer[Berry] = new ListBuffer[Berry]()
    stock += new BlueBerry
    stock += new BlueBerry
    val bad = new Bad(AlwaysMad)
    val badPig = new Pig(stock, bad)
    val badPigHouse = new PigHouse(badPig)
    arthur.trunk.length shouldBe 0
    badPigHouse.receiveVisitor(arthur)
    arthur.trunk.length shouldBe 0
    arthur.healthLevel should be (90)
  }

  "A bad pig" should "not damage the character when he's not mad" in {
    object NeverMad extends CustomRandom {
      override def wantsToHurt(): Int = 0
    }

    val arthur = new Character
    var stock: ListBuffer[Berry] = new ListBuffer[Berry]()
    stock += new BlueBerry
    stock += new BlueBerry
    val bad = new Bad(NeverMad)
    val badPig = new Pig(stock, bad)
    val badPigHouse = new PigHouse(badPig)
    arthur.trunk.length shouldBe 0
    badPigHouse.receiveVisitor(arthur)
    arthur.trunk.length shouldBe 0
    arthur.healthLevel should be (100)
  }

  "A pig" should "return its quantity of food energy" in {
    var stock: ListBuffer[Berry] = new ListBuffer[Berry]()
    stock += new BlueBerry
    stock += new BlueBerry
    stock += new BlackBerry(40)
    val pig = new Pig(stock, new Happy)
    pig.quantityOfFoodEnergy shouldBe 44
  }

}
