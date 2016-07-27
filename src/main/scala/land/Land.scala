package land

import elements.trees.Tree
import elements.{Element, Character}
import elements.berries.Berry
import elements.pigs.PigHouse

import scala.collection.mutable.ListBuffer

class Land(val x: Int, val y: Int) {
  var coordinates: ListBuffer[Position] = new ListBuffer[Position]

  def positionsWithElementsSurrounding(posx: Int, posy: Int): ListBuffer[Position] ={
    getPositionsBy({ p => near(positionAt(posx, posy), p) & !p.isEmpty })
  }

  def near(origin: Position, pos: Position): Boolean ={
    Math.sqrt(
      Math.pow(
        Math.abs(pos.x - origin.x), 2) +
      Math.pow(
        Math.abs(pos.y - origin.y), 2)
    ) < 100
  }

  def foodEnergySurrounding(posx: Int, posy: Int): Int = {
    getBerriesNear(posx, posy).map( f => f.pointsOfEnergy ).sum
  }

  //TODO: asegurarse de que el content de la posición donde se guarda la comida es un Character
  def pickUpFoodFor(posx: Int, posy: Int) = {
    val character: Character = positionAt(posx, posy).content.asInstanceOf [Character]
    getBerriesNear(posx, posy).map( f => character.addFood(f) )
  }

 def bestPigHouse: PigHouse = {
    val pigHouses: ListBuffer[PigHouse] = getPositionsBy({ p => p.content.isInstanceOf[PigHouse] }).map(
      p => p.content.asInstanceOf[PigHouse])
    pigHouses.maxBy(p => p.pig.quantityOfFoodEnergy)
  }

  def treesSurrounding(posx: Int, posy: Int): Int = {
    getPositionsBy( p => p.content.isInstanceOf [Tree]).length
  }

  def placeElementAt(elem: Element, posx: Int, posy: Int) = {
    positionAt(posx, posy).placeElement(elem)
  }


  def positionAt(posx: Int, posy: Int): Position ={
    val res = coordinates.find( p => p.x == posx && p.y == posy )
    var ret: Position = null
    if (res.isEmpty) {
      ret = new Position(posx, posy)
      coordinates += ret
    } else {
      ret = coordinates.head
    }
    ret
  }

  def getPositionsBy(f: Position => Boolean) ={
    coordinates.filter( f )
  }

  def getBerriesNear(posx: Int, posy: Int): ListBuffer[Berry] = {
    val positions: ListBuffer[Position] =
      getPositionsBy({ p => near(positionAt(posx, posy), p) & p.content.isInstanceOf [Berry] })
    positions.map( p => p.content.asInstanceOf [Berry] )
  }
}