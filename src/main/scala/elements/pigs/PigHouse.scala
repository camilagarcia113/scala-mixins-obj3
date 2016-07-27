package elements.pigs

import elements.{Character, Element}

class PigHouse(val pig: Pig) extends Element{

  def receiveVisitor(character: Character): Unit ={
    pig.meetCharacter(character)
  }
}
