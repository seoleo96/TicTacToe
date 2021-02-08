package com.example.tictactoe.di

import android.app.Application
import com.example.tictactoe.GameObjects

class DI : Application(){
    val gameObjects = GameObjects.getInstance()
}