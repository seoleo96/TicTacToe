package com.example.tictactoe.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tictactoe.R
import com.example.tictactoe.model.UserRecord

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    var userList = mutableListOf<UserRecord>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_recorrds, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = userList[position]
        holder.setup(user)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun submitList(newList: MutableList<UserRecord>) {
        userList = newList
        notifyDataSetChanged()
    }

    class MyViewHolder(val view: View):RecyclerView.ViewHolder(view){
        fun setup(user: UserRecord) {
            view.findViewById<TextView>(R.id.name).text = user.name
            view.findViewById<TextView>(R.id.textView2).text = user.wins.toString()
            view.findViewById<TextView>(R.id.textView3).text = user.loses.toString()
        }
    }

}