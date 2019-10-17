import org.scalatest.FunSuite
import model._

class TestParty extends FunSuite {

  test("defeatParty with all live victors") {
    val characterOne: Character = new Character("PartyChar1", 10, 10, 10, 10, 10, 10)
    val characterTwo: Character = new Character("PartyChar2", 10, 10, 10, 10, 10, 10)
    val characterThree: Character = new Character("PartyChar3", 10, 10, 10, 10, 10, 10)
    val winningParty: Party = new Party(List(characterOne, characterTwo, characterThree))

    val characterFour: Character = new Character("PartyChar1", 10, 10, 10, 10, 10, 10)
    val characterFive: Character = new Character("PartyChar2", 10, 10, 10, 10, 10, 10)
    val characterSix: Character = new Character("PartyChar3", 10, 10, 10, 10, 10, 10)
    characterFour.gainXP(10)
    characterFive.gainXP(10)
    characterSix.gainXP(10)
    characterFour.isAlive = false
    characterFive.isAlive = false
    characterSix.isAlive = false
    val losingParty: Party = new Party(List(characterFour, characterFive, characterSix))

    winningParty.defeatParty(losingParty)

    assert(winningParty.getParty.head.getXP == 10, "XP distributed incorrectly")
    assert(winningParty.getParty(1).getXP == 10, "XP distributed incorrectly")
    assert(winningParty.getParty(2).getXP == 10, "XP distributed incorrectly")
  }

  test("defeatParty with a dead victor") {
    val characterOne: Character = new Character("PartyChar1", 10, 10, 10, 10, 10, 10)
    val characterTwo: Character = new Character("PartyChar2", 10, 10, 10, 10, 10, 10)
    val characterThree: Character = new Character("PartyChar3", 10, 10, 10, 10, 10, 10)
    characterTwo.isAlive = false
    val winningParty: Party = new Party(List(characterOne, characterTwo, characterThree))

    val characterFour: Character = new Character("PartyChar1", 10, 10, 10, 10, 10, 10)
    val characterFive: Character = new Character("PartyChar2", 10, 10, 10, 10, 10, 10)
    val characterSix: Character = new Character("PartyChar3", 10, 10, 10, 10, 10, 10)
    characterFour.gainXP(10)
    characterFive.gainXP(10)
    characterSix.gainXP(10)
    characterFour.isAlive = false
    characterFive.isAlive = false
    characterSix.isAlive = false
    val losingParty: Party = new Party(List(characterFour, characterFive, characterSix))

    winningParty.defeatParty(losingParty)

    assert(winningParty.getParty.head.getXP == 15, "XP distributed incorrectly")
    assert(winningParty.getParty(1).getXP == 0, "XP distributed incorrectly")
    assert(winningParty.getParty(2).getXP == 15, "XP distributed incorrectly")
  }

  test("defeatParty with all dead victors") {
    val characterOne: Character = new Character("PartyChar1", 10, 10, 10, 10, 10, 10)
    val characterTwo: Character = new Character("PartyChar2", 10, 10, 10, 10, 10, 10)
    val characterThree: Character = new Character("PartyChar3", 10, 10, 10, 10, 10, 10)
    characterOne.isAlive = false
    characterTwo.isAlive = false
    characterThree.isAlive = false
    val winningParty: Party = new Party(List(characterOne, characterTwo, characterThree))

    val characterFour: Character = new Character("PartyChar1", 10, 10, 10, 10, 10, 10)
    val characterFive: Character = new Character("PartyChar2", 10, 10, 10, 10, 10, 10)
    val characterSix: Character = new Character("PartyChar3", 10, 10, 10, 10, 10, 10)
    characterFour.gainXP(10)
    characterFive.gainXP(10)
    characterSix.gainXP(10)
    characterFour.isAlive = false
    characterFive.isAlive = false
    characterSix.isAlive = false
    val losingParty: Party = new Party(List(characterFour, characterFive, characterSix))

    winningParty.defeatParty(losingParty)

    assert(winningParty.getParty.head.getXP == 0, "XP should not have been awarded")
    assert(winningParty.getParty(1).getXP == 0, "XP should not have been awarded")
    assert(winningParty.getParty(2).getXP == 0, "XP should not have been awarded")
  }
}
