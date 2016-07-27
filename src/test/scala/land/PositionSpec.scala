package land

import elements.berries.BlueBerry
import org.scalatest.{FlatSpec, Matchers}

class PositionSpec extends FlatSpec with Matchers {

  "A position" should "be initialized with the coordinates given" in {
    val position = new Position(3, 4)
    position.x should be (3)
    position.y should be (4)
  }

  "A position" should "be initialized with noElement as its content" in {
    val position = new Position(3, 4)
    position.content shouldBe noElement
  }

  "A position" should "be able to place an Element as its content" in {
    val position = new Position(3, 4)
    position.content shouldBe noElement
    position.placeElement(new BlueBerry)
    position.content shouldBe a [BlueBerry]
  }

  "A position" should "not place an Element if it is not empty" in {
    val position = new Position(3, 4)
    position.placeElement(new BlueBerry)
    position.isEmpty shouldBe false
    val thrown = intercept[Exception] { position.placeElement(new BlueBerry) }
    thrown.getMessage shouldBe "Position already has an element"
  }
}
