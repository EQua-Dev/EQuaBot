package com.androidstrike.equabot.utils

import java.sql.Timestamp
import java.text.SimpleDateFormat

object Time {

    fun timeStamp(): String{
        val timeStamp = Timestamp(System.currentTimeMillis())
        val sdf = SimpleDateFormat("HH:mm")
        val time = sdf.format(timeStamp.time)

        return time.toString()
    }
}