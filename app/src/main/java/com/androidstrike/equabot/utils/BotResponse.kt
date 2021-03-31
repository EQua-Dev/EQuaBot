package com.androidstrike.equabot.utils

import com.androidstrike.equabot.utils.Constants.OPEN_GOOGLE
import com.androidstrike.equabot.utils.Constants.OPEN_SEARCH
import java.lang.Exception

object BotResponse {

    fun basicResponses(_message: String): String{

        val random = (0..2).random() //for the 3 possible responses to each question
        val message = _message.toLowerCase() //changes all text entered by user to lowercase for easier processing

        return  when { //each possible entry by the user and response
            //Hello
            message.contains("hello") -> {
                when (random){
                    0 -> "Hello"
                    1 -> "Hi There!"
                    2 -> "Ndewo!"
                    else -> "Chill, i think something is stuck in my brain...ask something else"
                }
            }
            //How are you
            message.contains("how are you") -> {
                when (random){
                    0 -> "I am very well, thank you!"
                    1 -> "Omo, there is no money on ground!"
                    2 -> "I am fine, How are you?"
                    else -> "Chill, i think something is stuck in my brain...ask something else"
                }
            }
            //Who made you
            message.contains("who made you") -> {
                when (random){
                    0 -> "I was put together by a group of smart brains at DevStrike, mastered by EQua"
                    1 -> "In the Catholic Cathecism Doctrine, i would say God made me. But I'm only a chat Bot of DevStrike "
                    2 -> "EQua is my maker, DevStrike is my home"
                    else -> "Chill, i think something is stuck in my brain...ask something else"
                }
            }
            //What can you do
            message.contains("what can you do") -> {
                when (random){
                    0 -> "Well, it depends on what you want. I can solve simple math equations, flip a coin, tell the time, search for terms or answer a few of your questions like i just did"
                    1 -> "I was told by my maker to be a simple companion to you. Flipping coins, solving simple arithmetic, searching stuff, and keeping you company by answering questions"
                    2 -> "I can solve simple math, search things and flip coins"
                    else -> "Chill, i think something is stuck in my brain...ask something else"
                }
            }
            message.contains("flip") && message.contains("coin") -> {
                var r = (0..1).random()
                val result = if (r == 0) "heads" else "tails"

                "I flipped a coin and guess what...it landed on $result"
            }
            message.contains("solve") -> {
                val equation: String? = message.substringAfter("solve") //gets only the value of string after the word "solve"

                return try {
                    val answer = SolveMath.solveMath(equation?: "0")
                    answer.toString()
                }catch (e: Exception){
                    "Sorry, I can't Solve that"
                }
            }
            //Get the current time
            message.contains("time") && message.contains("?") ->{
                Time.timeStamp()
            }
            //Open Google
            message.contains("open") && message.contains("google") -> {
                OPEN_GOOGLE
            }
            //Open Search
            message.contains("search") -> {
                OPEN_SEARCH
            }
            else -> {
                when (random){ //user types something not programmed
                    0 -> "Sorry, I do not have the capabilities yet to comprehend that"
                    1 -> "Ask me something else...something easier. I am still an infant"
                    2 -> "Chill, i think something is stuck in my brain...ask something else"
                    else -> "error"
                }
            }
        }

    }

}