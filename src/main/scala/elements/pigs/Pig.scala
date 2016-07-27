package elements.pigs

import elements.{Fighter, Character}
import elements.berries.Berry

import scala.collection.mutable.ListBuffer

class Pig(var foodStock: ListBuffer[Berry], val mood: Mood) extends Fighter {
  var health: Int = 100

  def meetCharacter(character: Character): Unit = {
    mood.receiveVisit(this, character)
  }

  def giveAwayFood: Berry ={
    foodStock.head
  }
  def removeFood: Unit ={
    foodStock -= foodStock.head
  }

  def quantityOfFoodEnergy: Int ={
    foodStock.map( f => f.pointsOfEnergy ).sum
  }
}
