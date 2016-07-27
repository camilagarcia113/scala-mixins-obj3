package elements

trait MinimumHealth extends Fighter {
  val minimum: Int = 10

  override def getDamagedBy(points: Int, attacker: Fighter) ={
    if(healthLevel - points < minimum) {
      healthLevel = minimum
    } else {
      super.getDamagedBy(points, attacker)
    }
  }
}

trait ResistantHealth extends Fighter {

  override def getDamagedBy(points: Int, attacker: Fighter) = super.getDamagedBy(points / 3, attacker)

}

trait DamageReboundHealth extends Fighter {
  var wasAttacked:Boolean = false
  // TODO para evitar stack overflow entre dos personajes con DamageReboundHealth, falla

  override def getDamagedBy(points: Int, attacker: Fighter) = {
    super.getDamagedBy(points, attacker)
    if(!wasAttacked) {
      attacker.getDamagedBy(points / 2, attacker)
    }
    wasAttacked = false
  }

}