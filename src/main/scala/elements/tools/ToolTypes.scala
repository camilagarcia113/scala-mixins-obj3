package elements.tools

trait Common {
  var weathering: Int

  def wearOut = weathering -= pointsOfWeathering.toInt

  def pointsOfWeathering : Double

}

trait Reinforced {

}

trait Iron {
  val resistance: Int
  var weathering: Int

  def pointsOfWeathering: Double
}