package dev.syntext_error.testproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.syntext_error.testproject.R
import dev.syntext_error.testproject.models.AnswersModel


class ResponseAdapter (private val context: Context, private val messages: List<AnswersModel>? ,private val cellClickListener: CellClickListener ) : RecyclerView.Adapter<ResponseAdapter.ViewHolder>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup, index: Int): ViewHolder {
        val rootView = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_item, viewGroup, false)
        return ViewHolder(rootView)
    }

    override fun getItemCount(): Int {
            return messages?.size!!
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, index: Int) {
        viewHolder.itemView.animation = AnimationUtils.loadAnimation(context,R.anim.item_animation_fall_down)
        viewHolder.messageTV.text = "My Response #" +messages?.get(index)?.id
        viewHolder.dateTV.text =  messages?.get(index)?.timeStamp
        viewHolder.itemView.setOnClickListener {
            val answersModel : AnswersModel? = messages?.get(index)
            cellClickListener.onCellClickListener(answersModel)
        }
    }


    inner class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView)
    {
        var messageTV: TextView = itemView.findViewById(R.id.my_response_1) as TextView
        var dateTV: TextView = itemView.findViewById(R.id.date) as TextView
    }

    interface CellClickListener {
        fun onCellClickListener(data: AnswersModel?)
    }

}