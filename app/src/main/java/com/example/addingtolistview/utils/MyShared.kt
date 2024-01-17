package com.example.addingtolistview.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken

object MySharedPreference {
    private const val NAME = "catch_file_name"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var list: ArrayList<String>
        get() = stringToList(preferences.getString("list", "[]")!!)
        set(value) = preferences.edit {
            it.putString("list", listToString(value))
        }

    private fun stringToList(str: String): ArrayList<String> {
        val gson = Gson()
        try {
            val jsonArray = JsonParser.parseString(str).asJsonArray
            return gson.fromJson(jsonArray, object : TypeToken<ArrayList<String>>() {}.type)
        } catch (e: JsonSyntaxException) {
            // Handle the case where the stored value is not a valid JSON array
            e.printStackTrace()
            return ArrayList()
        }
    }

    private fun listToString(list: ArrayList<String>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}