package com.example.tictactoe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe.di.DI

class MainActivity : AppCompatActivity() {
    private lateinit var go: GameObjects
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        go = DI().gameObjects
    }

    override fun onDestroy() {
        super.onDestroy()
        go.resetUserlist()
    }

}