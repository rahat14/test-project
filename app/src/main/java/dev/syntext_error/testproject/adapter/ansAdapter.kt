package dev.syntext_error.testproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.syntext_error.testproject.R


class ansAdapter(private val context: Context, private val messages: ArrayList<String>) : RecyclerView.Adapter<ansAdapter.ViewHolder>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup, index: Int): ViewHolder {
        val rootView = LayoutInflater.from(viewGroup.context).inflate(R.layout.ans_item, viewGroup, false)
        return ViewHolder(rootView)
    }

    override fun getItemCount(): Int {
            return messages.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, index: Int) {
//        viewHolder.itemView.animation = AnimationUtils.loadAnimation(context,R.anim.item_animation_fall_down)
        viewHolder.messageTV.text = "Qus : " + (index+1)
      viewHolder.qusTV.text =  messages?.get(index)
    }


    inner class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView)
    {
        var messageTV: TextView = itemView.findViewById(R.id.my_response_1) as TextView
        var qusTV: TextView = itemView.findViewById(R.id.qustion) as TextView
    }



}