package elements

import elements.rocks.{Gold, Rock, RockBlock}
import org.scalatest.{Matchers, FlatSpec}

import scala.collection.mutable.ListBuffer

class RockBlockSpec extends FlatSpec with Matchers {

  "A rock block" should "have resistance points when instantiated" in {
    val rock: RockBlock = new RockBlock(30)
    rock.resistance shouldBe 30
  }

  "A rock block" should "decrease its resistance points when being hit" in {
    val rock: RockBlock = new RockBlock(30)
    rock.getHit(20)
    rock.resistance shouldBe 10
  }

  "A rock block" should "give 3 to 5 rocks when loosing all of its resistance points" in {
    val rock: RockBlock = new RockBlock(30)
    var rocksGiven: ListBuffer[Rock] = rock.getHit(5)
    rocksGiven.isEmpty shouldBe true
    rocksGiven = rock.getHit(25)
    rocksGiven.isEmpty shouldBe false
    rocksGiven.size shouldBe >= (3)
    rocksGiven.size shouldBe <= (5)
  }

  "A rock block" should "be able to give gold in addition to rocks" in {
    object GenerousRockBlock extends RockBlock(30) {
      override val generatesGold: Boolean = true
    }

    val rock: RockBlock = GenerousRockBlock
    val rocksGiven: ListBuffer[Rock] = rock.getHit(30)
    rocksGiven.count( r => r.isInstanceOf[Gold]) shouldBe 1
  }

}
