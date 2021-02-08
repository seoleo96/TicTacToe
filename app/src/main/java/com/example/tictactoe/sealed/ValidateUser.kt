package com.example.tictactoe.sealed

sealed class ValidateUser{
    object Empty : ValidateUser()
    object SameName : ValidateUser()
    object Ok : ValidateUser()
}
