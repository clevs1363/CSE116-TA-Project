package model

import scala.annotation.tailrec

class Character(name: String, var attPow: Int, var defPow: Int, var magAttPow: Int, var magDefPow: Int, var maxHP: Double, var maxMP: Int) {

  var currentHP: Double = maxHP
  var currentMP: Int = maxMP
  var isAlive: Boolean = true
  private var xp: Int = 0
  private var level: Int = 1

  private def takeDamage(attackingCharacter: Character, damage: Double): Unit = {
    currentHP -= damage
    if (currentHP <= 0) {
      isAlive = false
      attackingCharacter.gainXP(attackingCharacter.determineXP(this))
    }
  }

  def physicalAttack(defendingCharacter: Character): Unit = {
    val damageToDeal: Double = ((attPow * 1.5) - defendingCharacter.defPow) / 10
    if (damageToDeal < 0) println("You did no damage!")
    else defendingCharacter.takeDamage(this, damageToDeal)
  }

  def magicAttack(defendingCharacter: Character): Unit = {
    val damageToDeal: Double = (magAttPow * 1.5) - defendingCharacter.magDefPow
    if (damageToDeal < 0) println("You did no magical damage!")
    else if (currentMP <= 0) println(this + " doesn't have enough magic points!") //TODO: different costs of different attacks
    else {
      defendingCharacter.takeDamage(this, damageToDeal)
      currentMP -= 1 //TODO decrease MP based on attack type
    }
  }

  def getXP: Int = xp

  def determineXP(character: Character): Int = {
    val amountOfXP = (character.level * character.defPow + 25) + attPow
    amountOfXP
  }

  def gainXP(xpToGain: Int): Unit = {
    if (xpToGain > 100) {
      xp = xpToGain - 100
      gainLevel()
      gainXP(xp)
    } else if (xp + xpToGain > 100) {
      gainLevel()
      xp = xp + xpToGain - 100
    } else {
      xp = xpToGain
    }
  }

  def getLevel: Int = level

  def gainLevel(): Unit = {
    //TODO add better level mechanics
    level += 1
    attPow += 5
    defPow += 5
    magAttPow += 1
    magDefPow += 1
    maxHP += 10
    maxMP += 3
  }

  override def toString: String = this.name

}
