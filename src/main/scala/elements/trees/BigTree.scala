package elements.trees

import elements.{CustomRandom, Wood}

class BigTree (resistance: CustomRandom) extends Tree(resistance) {

  var health: Int = resistance.bigTreeRandom()
  //val woodQuantity = resistance.bigTreeRandom()
  val woodenPieces = {
    Array.fill[Wood](resistance.bigTreeRandom())(new Wood)
  }

  def getHit(damagePoints: Int): Array[Wood] ={
    subtractHealthPoints(damagePoints)
    woodenPieces
  }

}
