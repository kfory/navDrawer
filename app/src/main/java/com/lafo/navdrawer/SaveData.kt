package com.lafo.dark

import android.content.Context
import android.content.SharedPreferences

class SaveData(context: Context) {

    private var sharedPreferences: SharedPreferences = context.getSharedPreferences("file", Context.MODE_PRIVATE)

    //This metod will save the nigth mode state: true o false
    fun setDarkModeState(state: Boolean?){
        val editor = sharedPreferences.edit()
        editor.putBoolean("Dark", state!!)
        editor.apply()
    }


    //this method will load the night mode state
    fun loadDarkModeState():Boolean?{
        val state = sharedPreferences.getBoolean("Dark", false)
        return (state)
    }

}