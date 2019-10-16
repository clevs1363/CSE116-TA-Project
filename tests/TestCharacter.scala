import org.scalatest.FunSuite
import model._

class TestCharacter extends FunSuite {

  val EPSILON: Double = 0.00000001

  def equalDouble(x1: Double, x2: Double): Boolean = {
    Math.abs(x1 - x2) < EPSILON
  }

  test("takeDamage") {
    val characterOne = new Character("Bob", 10, 10, 10, 10, 10, 10)
    characterOne.takeDamage(5)
    assert(characterOne.isAlive, "Character should be alive")
    assert(characterOne.currentHP == 5, "Character HP is wrong")

    characterOne.takeDamage(5)
    assert(!characterOne.isAlive, "Character should be dead")
    assert(characterOne.currentHP == 0, "Character HP is wrong")

    characterOne.takeDamage(1)
    assert(!characterOne.isAlive, "Character should be dead")
    assert(characterOne.currentHP == -1, "Character HP is wrong")

    val characterTwo = new Character("Billy",10, 10, 10, 10, 10, 10)
    characterTwo.takeDamage(10)
    assert(!characterTwo.isAlive, "Character should be dead")
    assert(characterTwo.currentHP == 0, "Character HP is wrong")
  }

  test("physicalAttack") {
    val characterOne: Character = new Character("Steve",50, 10, 5, 0, 25, 3)
    val characterTwo: Character = new Character("Rich",30, 30, 5, 5, 20, 5)

    characterOne.physicalAttack(characterTwo)
    assert(equalDouble(characterTwo.currentHP, 15.5), "HP was not as expected")

    assert(characterOne.currentMP == 3, "MP should not have changed")
    characterOne.magicAttack(characterTwo)
    assert(equalDouble(characterTwo.currentHP, 13.0), "HP from magic attack not as expected")
    assert(characterOne.currentMP == 2, "MP should have changed")

    characterOne.currentMP = 0
    characterOne.magicAttack(characterTwo)
    assert(equalDouble(characterTwo.currentHP, 13.0), "Character should not have taken any damage")
  }

}
