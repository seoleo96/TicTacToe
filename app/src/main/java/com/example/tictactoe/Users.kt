package com.example.tictactoe

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Users(
    val firstUser : String,
    val secondUser : String
):Parcelable
