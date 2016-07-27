package elements

import elements.berries.{NoBerry, Berry}
import elements.pigs.PigHouse
import elements.rocks.Rock
import scala.collection.mutable.ListBuffer

abstract class Fighter extends Element {
  var healthLevel: Int = 100
  def getDamagedBy(points: Int, attacker: Fighter) ={
    healthLevel -= points
  }

  def getBetterBy(points: Int) = {
    healthLevel += points
  }
}

class Character extends Fighter {
  var trunk: ListBuffer[Element] = new ListBuffer[Element]

  def visitAPigHouse(house: PigHouse): Unit ={
    house.receiveVisitor(this)
  }

  def addFood(food: Berry): Unit = {
    if(! food.isInstanceOf[NoBerry])
      trunk.append(food)
  }

  def collectStone(stone: Rock): Unit ={
    trunk += stone
  }

  def collectGrass(grass: CutGrass): Unit ={
    trunk += grass
  }

  def canBuildACampFire: Boolean ={
    getElements[Rock].length >= 2 & getElements[Grass].length >= 3
  }

  def buildACampfire() ={
    if(canBuildACampFire) {
      useStonesForCampfire()
      useGrassForCampfire()
      trunk += new Campfire
    }
  }

  private
   def useGrassForCampfire(): Unit ={
    val grass: ListBuffer[Grass] = getElements[Grass]
    for(i <- 1 to 3){
      trunk -= grass.head
      grass -= grass.head
    }
  }

  def useStonesForCampfire(): Unit ={
    val stones: ListBuffer[Rock] = getElements[Rock]
    for(i <- 1 to 2){
      trunk -= stones.head
      stones -= stones.head
    }
  }

  def getElements[T <: Element](implicit m: Manifest[T]): ListBuffer[T] = {
    trunk.collect{ case e: T => e }
  }
}

