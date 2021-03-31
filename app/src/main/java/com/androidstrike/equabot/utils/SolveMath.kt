package com.androidstrike.equabot.utils

import android.util.Log

//object to enable the bot solve simple math equations from the user request
object SolveMath {

    fun solveMath(equation: String) : Int{

        //remove all empty spaces
        val newEquation = equation.replace(" ", "")
        Log.d("Math", newEquation)

        //check the operator in the query and then split the digits on each side of the operator and solve
        return when {
            newEquation.contains("+") -> {
                val split = newEquation.split("+")
                val result = split[0].toInt() + split[1].toInt()
                result
            }
            newEquation.contains("-") -> {
                val split = newEquation.split("-")
                val result = split[0].toInt() - split[1].toInt()
                result
            }
            newEquation.contains("*") -> {
                val split = newEquation.split("*")
                val result = split[0].toInt() * split[1].toInt()
                result
            }
            newEquation.contains("/") -> {
                val split = newEquation.split("/")
                val result = split[0].toInt() / split[1].toInt()
                result
            }
            else -> {
                0
            }
        }
    }
}