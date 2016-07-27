package land

import elements.Element

object noElement extends Element

class Position(val x: Int, val y: Int) {
  var content: Element = noElement

  def isEmpty: Boolean = {
    content == noElement
  }

  def placeElement(elem: Element) ={
    if(isEmpty) {
      content = elem
    } else {
      throw new Exception("Position already has an element")
    }
    this
  }

  def removeElement ={
    content = noElement
  }

}
