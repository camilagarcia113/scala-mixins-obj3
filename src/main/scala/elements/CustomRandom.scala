package elements

import scala.util.Random

class CustomRandom {
  // TODO refactor para que se aplique a cada clase en particular

  def wantsToHurt: Int = Random.nextInt(2)

  def smallTreeRandom(): Int ={
    val range = 3 to 5
    Random.shuffle(range.toList).head
  }

  def bigTreeRandom(): Int ={
    val range = 5 to 10
    Random.shuffle(range.toList).head
  }

  def rocksOnBreaking(): Int = Random.nextInt(5-3) + 3

  def generatesGold(): Boolean = Random.nextBoolean()
}
