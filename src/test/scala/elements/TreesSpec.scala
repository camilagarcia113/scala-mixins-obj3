package elements

import elements.trees.{SmallTree, BigTree}
import org.scalatest.{Matchers, FlatSpec}

class TreesSpec extends FlatSpec with Matchers {

  object CustomSmallTree extends CustomRandom {
    override def smallTreeRandom(): Int = 4
  }

  object CustomBigTree extends CustomRandom {
    override def bigTreeRandom(): Int = 8
  }

  val smallTree: SmallTree = new SmallTree(CustomSmallTree)
  val bigTree: BigTree = new BigTree(CustomBigTree)


  "A small tree" should "be created with some resistance points" in {
    smallTree.health shouldBe 4
  }

  "A small tree" should "be cut once it has received more than its resistance" in {
    smallTree.getHit(4)
    smallTree.health shouldBe 0
    //TODO: Deberia haber un cut tree cuando se corta
  }

  "A small tree" should "give 3 pieces of wood when hit" in {
    val woodenPieces = smallTree.getHit(1)
    woodenPieces.length shouldBe 3
    woodenPieces(1).isInstanceOf[Wood]
  }

  "A big tree" should "be created with some resistance points" in {
    bigTree.health shouldBe 8
  }

  "A big tree" should "be cut once it has received more than its resistance" in {
    bigTree.getHit(8)
    bigTree.health shouldBe 0
    //TODO: Deberia haber un cut tree cuando se corta
  }

  "A big tree" should "give 5 to 10 pieces of wood when hit" in {
    val woodenPieces = bigTree.getHit(1)
    assert(woodenPieces.length >=5 && woodenPieces.length <= 10)
    woodenPieces(1).isInstanceOf[Wood]
  }
}
