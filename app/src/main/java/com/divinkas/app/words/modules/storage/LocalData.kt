package com.divinkas.app.words.modules.storage

import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import java.util.*

open class LocalData(private val sharedPreferences: SharedPreferences) {

    private val gson: Gson = Gson()

    fun setCacheModel(obj: Objects) {
        cacheObject(obj.javaClass.canonicalName, gson.toJson(obj))
    }

    fun <T> getCacheModel(c: Class<T>): T? {
        return try {
            gson.fromJson<T>(getObject(c.canonicalName), c)
        } catch (e: Exception) {
            Log.e("Error saving: ", e.message!!)
            null
        }
    }

    private fun cacheObject(key: String?, value: String) {
        if (key != null)
            sharedPreferences.edit().putString(key, value).apply()
    }

    private fun getObject(key: String?): String? {
        return if (key != null)
            sharedPreferences.getString(key, null)
        else
            null
    }
}