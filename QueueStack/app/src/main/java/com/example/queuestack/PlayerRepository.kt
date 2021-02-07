package com.example.queuestack

class PlayerRepository {

    companion object {
        var records: MutableList<Player> = ArrayList()

        fun addLoss(name: String){
            records.find { it.name == name }!!.loss++
        }

        fun addWin(name: String){
            records.find { it.name == name }!!.win++
        }

        fun addDraw(name: String){
            records.find { it.name == name }!!.draw++
        }

        fun addPlayer(playerName: String){
            if (records.find{ it.name == playerName} == null){
                records.add(Player(name = playerName))
            }
        }
    }
}