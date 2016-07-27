package elements.trees

import elements.{CustomRandom, Wood}

class SmallTree(resistance: CustomRandom) extends Tree(resistance) {

  var health: Int = resistance.smallTreeRandom()
  val woodenPieces = {
    Array(new Wood, new Wood, new Wood)
  }

  def getHit(damagePoints: Int): Array[Wood] ={
    subtractHealthPoints(damagePoints)
    woodenPieces
  }
}
