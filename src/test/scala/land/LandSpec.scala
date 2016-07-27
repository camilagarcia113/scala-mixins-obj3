package land

import elements.pigs._
import elements.trees.SmallTree
import elements.{CustomRandom, Character}
import elements.berries.{NoBerry, Berry, BlueBerry, BlackBerry}
import org.scalatest.{FlatSpec, Matchers}
import scala.collection.mutable.ListBuffer

class LandSpec extends FlatSpec with Matchers {

  "A land" should "be initialized with the given size" in {
    val land= new Land(20, 20)
    land.x should be (20)
    land.y should be (20)
  }

  "A land" should "have a coordinates board according to its size" in {
    val land = new Land(20, 30)
    land.x should be (20)
    land.y should be (30)
  }

  "An empty land" should "be able to tell when a position has no elements surrounding it" in {
    val land = new Land(20, 20)
    land.positionsWithElementsSurrounding(0, 0).length should be (0)
  }

  "A land with elements" should "be able to tell when a position has no elements surrounding it" in {
    val land = new Land(200, 200)
    land.placeElementAt(new BlueBerry, 0, 0)
    land.placeElementAt(new BlueBerry, 199, 199)
    land.positionsWithElementsSurrounding(100, 100).length should be (0)
  }

  "A land" should "be able to tell when a position has elements surrounding it" in {
    val land = new Land(20, 20)
    land.placeElementAt(new BlueBerry, 15, 15)
    land.placeElementAt(new BlueBerry, 19,19)
    land.positionsWithElementsSurrounding(0, 0).length should be (2)
  }

  "A land" should "be able to count total food energy on a positions' surroundings" in {
    val land = new Land(200,200)
    land.placeElementAt(new BlueBerry, 15,15)
    land.placeElementAt(new BlackBerry(20), 20,50)
    land.placeElementAt(new BlueBerry, 199,199)
    land.foodEnergySurrounding(30,2) should be (22)
  }

  "A land" should "be able to let a character pick up the food near him" in {
    val arthur = new Character
    val land = new Land(10, 10)
    land.placeElementAt(arthur, 6, 3)
    land.placeElementAt(new BlueBerry, 1, 3)
    land.placeElementAt(new BlackBerry(30), 7, 5)
    land.placeElementAt(new NoBerry, 9, 9)
    arthur.trunk.length should be(0)
    land.pickUpFoodFor(6, 3)
    arthur.trunk.length should be(2)
  }

  "A land" should  "be able to know which pig house is better provisioned" in {
    val land = new Land(10, 10)

    var stock: ListBuffer[Berry] = new ListBuffer[Berry]()
    stock += new BlackBerry(3)
    stock += new BlueBerry
    val happyPig = new Pig(stock, new Happy)

    val happyPigHouse1 = new PigHouse(happyPig)

    var stock2: ListBuffer[Berry] = new ListBuffer[Berry]()
    stock2 += new BlackBerry(4)
    stock2 += new BlackBerry(30)
    stock2 += new BlueBerry
    val happyPig2 = new Pig(stock2, new Happy)
    val happyPigHouse2 = new PigHouse(happyPig2)

    var stock3: ListBuffer[Berry] = new ListBuffer[Berry]()
    stock3 += new BlackBerry(4)
    val neutralPig = new Pig(stock3, new Neutral)
    val neutralPigHouse = new PigHouse(neutralPig)

    var stock4: ListBuffer[Berry] = new ListBuffer[Berry]()
    stock4 += new BlueBerry
    val badPig = new Pig(stock3, new Bad(new CustomRandom))
    val badMoodPigHouse = new PigHouse(badPig)

    land.placeElementAt(neutralPigHouse, 1, 1)
    land.placeElementAt(happyPigHouse1, 5, 9)
    land.placeElementAt(badMoodPigHouse, 6, 4)
    land.placeElementAt(happyPigHouse2, 3, 3)

    land.bestPigHouse should be (happyPigHouse2)
  }

  "A land" should "be able to count the trees surrounding a position" in {
    val land = new Land(200, 200)
    land.placeElementAt(new SmallTree(new CustomRandom), 100, 100)
    land.placeElementAt(new SmallTree(new CustomRandom), 35, 48)
    land.placeElementAt(new SmallTree(new CustomRandom), 152, 138)
    land.treesSurrounding(95, 96) should be (3)
  }
}