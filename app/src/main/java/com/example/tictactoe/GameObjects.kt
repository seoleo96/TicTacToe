package com.example.tictactoe

import com.example.tictactoe.enumclass.WinningLine
import com.example.tictactoe.model.UserRecord

class GameObjects {
    private var userList = mutableMapOf<String, UserRecord>()

    fun addUser(user: UserRecord) {
        userList.put(user.name, user)
    }

    fun containsUser(name : String): Boolean {
        return userList.containsKey(name)
    }

    fun getUserRecord(name: String): UserRecord? {
        return userList.get(name)
    }



    fun getUserList(): MutableList<UserRecord> {
        return userList.values.toMutableList()
    }

    fun resetUserlist() {
        userList = mutableMapOf<String, UserRecord>()
    }

    companion object {
        @Volatile
        private var INSTANCE: GameObjects? = null

        fun getInstance(): GameObjects {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = GameObjects()
                }
            }
            return INSTANCE!!
        }
    }

    private var _currentPlayer = 1

    fun setCurrentPlayer(){
        _currentPlayer = 3 - currentPlayer
    }
    val currentPlayer
        get() = _currentPlayer
    private var _state = mutableListOf(
        0, 0, 0,
        0, 0, 0,
        0, 0, 0
    )
    val state
        get() = _state

    var player1Points = 0
    var player2Points = 0

    fun hasGameEnded(): WinningLine? {
        if (state[0] == currentPlayer && state[1] == currentPlayer && state[2] == currentPlayer) {
            return WinningLine.ROW_0
        } else if (state[3] == currentPlayer && state[4] == currentPlayer && state[5] == currentPlayer) {
            return WinningLine.ROW_1
        } else if (state[6] == currentPlayer && state[7] == currentPlayer && state[8] == currentPlayer) {
            return WinningLine.ROW_2
        } else if (state[0] == currentPlayer && state[3] == currentPlayer && state[6] == currentPlayer) {
            return WinningLine.COLUMN_0
        } else if (state[1] == currentPlayer && state[4] == currentPlayer && state[7] == currentPlayer) {
            return WinningLine.COLUMN_1
        } else if (state[2] == currentPlayer && state[5] == currentPlayer && state[8] == currentPlayer) {
            return WinningLine.COLUMN_2
        } else if (state[0] == currentPlayer && state[4] == currentPlayer && state[8] == currentPlayer) {
            return WinningLine.DIAGONAL_LEFT
        } else if (state[2] == currentPlayer && state[4] == currentPlayer && state[6] == currentPlayer) {
            return WinningLine.DIAGONAL_RIGHT
        }
        return null
    }

    fun resetObj(){
        _currentPlayer = 1
        _state = mutableListOf(
            0, 0, 0,
            0, 0, 0,
            0, 0, 0
        )
    }

    fun resetPoints(){
        player1Points = 0
        player2Points = 0
    }
}