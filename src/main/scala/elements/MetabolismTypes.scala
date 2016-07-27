package elements

trait SuperEfficientMetabolism extends Fighter {

  override def getBetterBy(points: Int) = {
    healthLevel += points * 2
  }
}

trait DeficientMetabolism extends Fighter {
  val percentage: Int = 100

  override def getBetterBy(points: Int) = {
    healthLevel += (points * (percentage * 0.01)).toInt
  }
}

trait LimitedMetabolism extends Fighter {
  val limit: Int = 100

  override def getBetterBy(points: Int) = {
    if(healthLevel + points > limit) {
      healthLevel = limit
    } else {
      healthLevel += points
    }
  }
}