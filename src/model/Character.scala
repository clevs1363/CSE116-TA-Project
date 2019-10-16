package model

class Character(name: String, var attPow: Int, var defPow: Int, var magAttPow: Int, var magDefPow: Int, var maxHP: Double, var maxMP: Int) {

  var currentHP: Double = maxHP
  var currentMP: Int = maxMP
  var isAlive: Boolean = true

  def takeDamage(damage: Double): Unit = {
    currentHP -= damage
    if (currentHP <= 0) isAlive = false
  }

  def physicalAttack(defendingCharacter: Character): Unit = {
    val damageToDeal: Double = ((attPow * 1.5) - defendingCharacter.defPow) / 10
    if (damageToDeal < 0) println("You did no damage!")
    else defendingCharacter.takeDamage(damageToDeal)
  }

  def magicAttack(defendingCharacter: Character): Unit = {
    val damageToDeal: Double = (magAttPow * 1.5) - defendingCharacter.magDefPow
    if (damageToDeal < 0) println("You did no magical damage!")
    else if (currentMP <= 0) println(this + " doesn't have enough magic points!") //TODO: different costs of different attacks
    else {
      defendingCharacter.takeDamage(damageToDeal)
      currentMP -= 1 //TODO decrease MP based on attack type
    }
  }

  override def toString: String = this.name

}
