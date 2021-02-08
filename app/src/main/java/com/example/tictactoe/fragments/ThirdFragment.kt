package com.example.tictactoe.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tictactoe.*
import com.example.tictactoe.databinding.FragmentThirdBinding
import com.example.tictactoe.di.DI
import com.example.tictactoe.enumclass.WinningLine
import com.example.tictactoe.model.UserRecord

class ThirdFragment : Fragment() {

    private var _binding: FragmentThirdBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var go: GameObjects
    private lateinit var user1: String
    private lateinit var user2: String
    private var point1 = 0
    private var point2 = 0
    private var historyPoint1Wins = 0
    private var historyPoint1Loses = 0
    private var historyPoint2Wins = 0
    private var historyPoint2Loses = 0

    private val args by navArgs<ThirdFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentThirdBinding.inflate(layoutInflater, container, false)
        go = DI().gameObjects
        historyUsersRecords()
        with(mBinding) {
            tvUserName.text = args.users.firstUser
            btn1.setOnClickListener { onBoxClicked(btn1, 1) }
            btn2.setOnClickListener { onBoxClicked(btn2, 2) }
            btn3.setOnClickListener { onBoxClicked(btn3, 3) }
            btn4.setOnClickListener { onBoxClicked(btn4, 4) }
            btn5.setOnClickListener { onBoxClicked(btn5, 5) }
            btn6.setOnClickListener { onBoxClicked(btn6, 6) }
            btn7.setOnClickListener { onBoxClicked(btn7, 7) }
            btn8.setOnClickListener { onBoxClicked(btn8, 8) }
            btn9.setOnClickListener { onBoxClicked(btn9, 9) }
            newGame.setOnClickListener {
                reset()
            }
            continueBtn.setOnClickListener {
                point1 = go.player1Points + historyPoint1Wins
                point2 = go.player2Points + historyPoint2Wins
                val user1Record = UserRecord(user1, point1, historyPoint1Loses + go.player2Points)
                val user2Record = UserRecord(user2, point2, historyPoint2Loses + go.player1Points)
                go.addUser(user1Record)
                go.addUser(user2Record)
                findNavController().navigate(R.id.action_thirdFragment_to_fourthFragment)
            }
            winning.text = args.users.firstUser
            winning2.text = args.users.secondUser
        }
        return mBinding.root
    }

    private fun historyUsersRecords() {
        if (go.containsUser(args.users.firstUser)) {
            user1 = go.getUserRecord(args.users.firstUser)!!.name
            historyPoint1Wins = go.getUserRecord(args.users.firstUser)!!.wins
            historyPoint1Loses = go.getUserRecord(args.users.firstUser)!!.loses
        } else {
            user1 = args.users.firstUser
            historyPoint1Wins = 0
            historyPoint1Loses = 0
        }

        if (go.containsUser(args.users.secondUser)) {
            user2 = go.getUserRecord(args.users.secondUser)!!.name
            historyPoint2Wins = go.getUserRecord(args.users.secondUser)!!.wins
            historyPoint2Loses = go.getUserRecord(args.users.secondUser)!!.loses
        } else {
            user2 = args.users.secondUser
            historyPoint2Wins = 0
            historyPoint2Loses = 0
        }
    }

    private fun disableBoxes() {
        with(mBinding) {
            btn1.isEnabled = false
            btn2.isEnabled = false
            btn3.isEnabled = false
            btn4.isEnabled = false
            btn5.isEnabled = false
            btn6.isEnabled = false
            btn7.isEnabled = false
            btn8.isEnabled = false
            btn9.isEnabled = false
        }
    }

    private fun onBoxClicked(btn: ImageView, i: Int) {

        if (go.currentPlayer == 1) {
            mBinding.tvUserName.text = args.users.secondUser
            go.state[i - 1] = go.currentPlayer
            btn.setImageResource(R.drawable.ic_x)
            btn.isEnabled = false
        } else {
            mBinding.tvUserName.text = args.users.firstUser
            go.state[i - 1] = go.currentPlayer
            btn.setImageResource(R.drawable.ic_o)
            btn.isEnabled = false
        }
        val winning = go.hasGameEnded()
        if (winning == null) {
            if (go.state.contains(0)) {
                go.setCurrentPlayer()
            } else {
                reset()
            }
        } else {
            when (go.currentPlayer) {
                1 -> go.player1Points++
                2 -> go.player2Points++
            }
            mBinding.textUser1.text = go.player1Points.toString()
            mBinding.textUser2.text = go.player2Points.toString()
            showWinner(winning)
            mBinding.continueBtn.visibility = View.VISIBLE
            disableBoxes()
        }
    }

    private fun reset() {
        with(mBinding) {
            btn1.setImageResource(0)
            btn2.setImageResource(0)
            btn3.setImageResource(0)
            btn4.setImageResource(0)
            btn5.setImageResource(0)
            btn6.setImageResource(0)
            btn7.setImageResource(0)
            btn8.setImageResource(0)
            btn9.setImageResource(0)
            btn1.isEnabled = true
            btn2.isEnabled = true
            btn3.isEnabled = true
            btn4.isEnabled = true
            btn5.isEnabled = true
            btn6.isEnabled = true
            btn7.isEnabled = true
            btn8.isEnabled = true
            btn9.isEnabled = true
            btn1.setBackgroundResource(0)
            btn2.setBackgroundResource(0)
            btn3.setBackgroundResource(0)
            btn4.setBackgroundResource(0)
            btn5.setBackgroundResource(0)
            btn6.setBackgroundResource(0)
            btn7.setBackgroundResource(0)
            btn8.setBackgroundResource(0)
            btn9.setBackgroundResource(0)
        }
        go.resetObj()

    }


    private fun showWinner(winningLine: WinningLine) {
        val (winningBoxes, background) = when (winningLine) {
            WinningLine.ROW_0 -> Pair(
                listOf(mBinding.btn1, mBinding.btn2, mBinding.btn3),
                R.drawable.horizontal_line
            )
            WinningLine.ROW_1 -> Pair(
                listOf(mBinding.btn4, mBinding.btn5, mBinding.btn6),
                R.drawable.horizontal_line
            )
            WinningLine.ROW_2 -> Pair(
                listOf(mBinding.btn7, mBinding.btn8, mBinding.btn9),
                R.drawable.horizontal_line
            )
            WinningLine.COLUMN_0 -> Pair(
                listOf(mBinding.btn1, mBinding.btn4, mBinding.btn7),
                R.drawable.vertical_line
            )
            WinningLine.COLUMN_1 -> Pair(
                listOf(mBinding.btn2, mBinding.btn5, mBinding.btn8),
                R.drawable.vertical_line
            )
            WinningLine.COLUMN_2 -> Pair(
                listOf(mBinding.btn3, mBinding.btn6, mBinding.btn9),
                R.drawable.vertical_line
            )
            WinningLine.DIAGONAL_LEFT -> Pair(
                listOf(mBinding.btn1, mBinding.btn5, mBinding.btn9),
                R.drawable.left_diagonal_line
            )
            WinningLine.DIAGONAL_RIGHT -> Pair(
                listOf(mBinding.btn3, mBinding.btn5, mBinding.btn7),
                R.drawable.right_diagonal_line
            )
        }

        winningBoxes.forEach { box ->
            box.background = ContextCompat.getDrawable(requireContext(), background)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}