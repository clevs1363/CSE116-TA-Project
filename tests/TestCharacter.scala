import org.scalatest.FunSuite
import model._

class TestCharacter extends FunSuite {

  val EPSILON: Double = 0.00000001

  def equalDouble(x1: Double, x2: Double): Boolean = {
    Math.abs(x1 - x2) < EPSILON
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

  test("gainXP") {
    val characterOne: Character = new Character("XPMonster", 10, 10, 10, 10, 10, 10)
    assert(characterOne.getXP == 0, "XP should initialize as 0")
    assert(characterOne.getLevel == 1, "Level should initialize to 1")

    characterOne.gainXP(50)
    assert(characterOne.getXP == 50, "XP did not gain correctly")

    characterOne.gainXP(75)
    assert(characterOne.getLevel == 2, "Character did not level up")
    assert(characterOne.getXP == 25, "Character XP is incorrect")

    // Test mutliple levels gained
    val characterTwo: Character = new Character("LevelMonster", 10, 10, 10, 10, 10, 10)
    assert(characterTwo.getXP == 0, "XP should initialize to 0")
    assert(characterTwo.getLevel == 1, "XP should initialize to 1")

    characterTwo.gainXP(450)
    assert(characterTwo.getLevel == 5, "Character did not level up mutliply correctly")

    val characterThree: Character = new Character("LevelMonster", 10, 10, 10, 10, 10, 10)
    val characterFour: Character = new Character("LevelMonster", 10, 10, 10, 10, 10, 10)
    assert(characterThree.determineXP(characterFour) == 45, "XP determined incorrectly")

    characterThree.gainXP(characterThree.determineXP(characterFour))
    assert(characterThree.getLevel == 1, "Character did not level correctly from determineXP")
    assert(characterThree.getXP == 45, "Character XP not correct after determineXP")


  }

}
