package com.androidstrike.equabot.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidstrike.equabot.R
import com.androidstrike.equabot.data.Message
import com.androidstrike.equabot.utils.BotResponse
import com.androidstrike.equabot.utils.Constants.OPEN_GOOGLE
import com.androidstrike.equabot.utils.Constants.OPEN_SEARCH
import com.androidstrike.equabot.utils.Constants.RECEIVE_ID
import com.androidstrike.equabot.utils.Constants.SEND_ID
import com.androidstrike.equabot.utils.Time
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MessagingAdapter
    private val botList = listOf("Luomy", "Ketem", "Chizzie", "Rossette")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView()

        clickEvents()

        val random = (0..3).random()
        customMessage("Hello! Today you're speaking with ${botList[random]}, what do you want to say?") //todo each bot will gave a different intro message

    }

    private fun clickEvents(){
        btn_send.setOnClickListener {
            sendMessage()
        }
        et_message.setOnClickListener {
            GlobalScope.launch {
                delay(100)
                withContext(Dispatchers.Main){
                    rv_messages.scrollToPosition(adapter.itemCount-1) //takes the screen view to the last message
                }
            }
        }
    }

    private fun recyclerView(){ // sets up the recyclerView
        adapter = MessagingAdapter()
        rv_messages.adapter = adapter
        rv_messages.layoutManager = LinearLayoutManager(applicationContext)
    }

    private fun sendMessage(){ //function to send the message from the user to the bot
        val message = et_message.text.toString() //get the user input
        val timeStamp = Time.timeStamp() //get the time message sent

        if (message.isNotEmpty()){
            et_message.setText("") //make the input field empty for next message

            adapter.insertMessage(Message(message, SEND_ID, timeStamp)) //update the screen with the message
            rv_messages.scrollToPosition(adapter.itemCount-1)

            botResponse(message)// take the message into the bot response object for the appropriate response
        }
    }

    private fun botResponse(message: String){
        val timeStamp = Time.timeStamp()//get the time of the particular message for future use (in case)

        //coroutine (runs services on a different thread the relays it to the main)
        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main){
                val response = BotResponse.basicResponses(message)

                adapter.insertMessage(Message(response, RECEIVE_ID, timeStamp)) //updates the adapter with the message, its ID and timestamp as defined in the "insertMessage" function in the adapter class
                rv_messages.scrollToPosition(adapter.itemCount-1) //takes the screen view to the last message

                when(response) {
                    OPEN_GOOGLE -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        site.data = Uri.parse("https://www.google.com/")
                        startActivity(site)
                    }
                    OPEN_SEARCH -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        val searchTerm: String? = message.substringAfter("search") //gets the term after the word "search"
                        site.data = Uri.parse("https://www.google.com/search?&q=$searchTerm")
                        startActivity(site)
                    }
                }
            }
        }

    }

    override fun onStart() { //to keep user at the bottom of the screen after user leaves app and returns
        super.onStart()
        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main){
                rv_messages.scrollToPosition(adapter.itemCount-1) //takes the screen view to the last message
            }
        }
    }

    private fun customMessage(message: String){
        //coroutine (runs services on a different thread the relays it to the main)
        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main){
                val timeStamp = Time.timeStamp() //get the time of the particular message for future use (in case)
                adapter.insertMessage(Message(message, RECEIVE_ID, timeStamp))

                rv_messages.scrollToPosition(adapter.itemCount-1) //takes the screen view to the last message
            }
        }
    }
}