package com.example.tictactoe.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.tictactoe.GameObjects
import com.example.tictactoe.Users
import com.example.tictactoe.sealed.ValidateUser
import com.example.tictactoe.databinding.FragmentSecondBinding
import com.example.tictactoe.di.DI

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val mBinding get() = _binding!!

    private lateinit var go : GameObjects

    private val EMPTY_USER = "Empty User! Please try again"
    private val SAME_NAME_USER = "Same User Names! Please try again"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(layoutInflater, container, false)

        go = DI().gameObjects

        with(mBinding) {

            startBt.setOnClickListener {
                val firstUser = user1.text.toString().trim()
                val secondUser = user2.text.toString().trim()
                when (validateUsers(firstUser, secondUser)) {
                    ValidateUser.Empty -> showToast(requireContext(), EMPTY_USER)
                    ValidateUser.SameName -> showToast(requireContext(), SAME_NAME_USER)
                    ValidateUser.Ok -> findNavController().navigate(
                        SecondFragmentDirections.actionSecondFragmentToThirdFragment(
                            Users(firstUser, secondUser)
                        )
                    )
                }
            }

            return mBinding.root

        }
    }

        private fun validateUsers(u1: String, u2: String): ValidateUser {
            if (u1.isEmpty() || u2.isEmpty()) return ValidateUser.Empty
            if (u1 == u2) return ValidateUser.SameName
            return ValidateUser.Ok
        }

        private fun showToast(context: Context, message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }


        override fun onDestroy() {
            super.onDestroy()
            _binding = null
        }
    }


