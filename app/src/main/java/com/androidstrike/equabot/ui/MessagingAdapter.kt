package com.androidstrike.equabot.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.androidstrike.equabot.R
import com.androidstrike.equabot.data.Message
import com.androidstrike.equabot.utils.Constants.RECEIVE_ID
import com.androidstrike.equabot.utils.Constants.SEND_ID
import kotlinx.android.synthetic.main.message_item.view.*

class MessagingAdapter: RecyclerView.Adapter<MessagingAdapter.MessageViewHolder>(){

    var messagesList = mutableListOf<Message>()
    //inner class created because we intend to implement some logic on the items within the recyclerView such as removing them
    inner class MessageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        init {

            itemView.setOnClickListener{
                //to remove a message from the screenwhen clicked
                messagesList.removeAt(adapterPosition)
                // use this (below) to add animation and also to simply update the item view and not the entire recycler view
                notifyItemRemoved(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent, false))
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        //logic to decide which side to display the message on
        //bot to left in green
        //user to right in red



        val currentMessage = messagesList[position]

        when (currentMessage.id){
            SEND_ID -> {
                holder.itemView.tv_message.apply {
                    text = currentMessage.message
                    visibility = View.VISIBLE
                }
                holder.itemView.tv_bot_message.visibility = View.GONE
            }

            RECEIVE_ID -> {
                holder.itemView.tv_bot_message.apply {
                    text = currentMessage.message
                    visibility = View.VISIBLE
                }
                holder.itemView.tv_message.visibility = View.GONE
            }
        }
    }

    override fun getItemCount(): Int {
        return messagesList.size
    }

    //function to add the new message to the list of messages in the recyclerView
    fun insertMessage(message: Message){
        this.messagesList.add(message)
        notifyItemInserted(messagesList.size)
    }
}



