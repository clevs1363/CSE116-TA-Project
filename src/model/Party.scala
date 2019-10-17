package model

class Party(party: List[Character]) {
  def defeatParty(defeatedParty: Party): Unit = {
    var totalXPPool: Int = 0
    for (character <- defeatedParty.getParty) totalXPPool += character.getXP

    var numOfLive: Int = 0
    for (character <- party) if (character.isAlive) numOfLive += 1
    if (numOfLive == 0) {
      println("There was no one left alive in your party!")
    } else for (character <- party) if (character.isAlive) character.gainXP(totalXPPool / numOfLive)
  }

  def getParty = party
}
