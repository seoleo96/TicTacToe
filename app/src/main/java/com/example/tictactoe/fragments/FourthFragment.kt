package com.example.tictactoe.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.tictactoe.GameObjects
import com.example.tictactoe.adapter.MyAdapter
import com.example.tictactoe.R
import com.example.tictactoe.databinding.FragmentFourthBinding
import com.example.tictactoe.di.DI


class FourthFragment : Fragment() {
    private var _binding: FragmentFourthBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var go : GameObjects
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFourthBinding.inflate(layoutInflater, container, false)

        go = DI().gameObjects
        val adapter = MyAdapter()
        val recyclerView = mBinding.recycler
        recyclerView.adapter = adapter
        adapter.submitList(go.getUserList())

        mBinding.floatingActionButton.setOnClickListener {
            go.resetPoints()
            go.resetObj()
//            go.resetUserlist()
            findNavController().navigate(R.id.action_fourthFragment_to_firstFragment)
        }

        return mBinding.root
    }

}