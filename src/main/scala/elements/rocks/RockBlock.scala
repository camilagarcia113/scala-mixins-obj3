package elements.rocks

import elements.CustomRandom

import scala.collection.mutable.ListBuffer

class RockBlock(var resistance: Int) {
  val rocksOnBreaking: Int = new CustomRandom().rocksOnBreaking()
  val generatesGold: Boolean = new CustomRandom().generatesGold()

  def getHit(points: Int): ListBuffer[Rock] = {
    var ret: ListBuffer[Rock] = new ListBuffer[Rock]
    resistance -= points
    if(resistance <= 0) {
      for (i <- 1 to rocksOnBreaking - 1) { ret += new Rock }
      if (generatesGold) { ret += new Gold } else { ret += new Rock }
    }
    return ret
  }

}
