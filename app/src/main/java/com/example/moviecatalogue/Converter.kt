package com.example.moviecatalogue

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

class Converter {
    @TypeConverter
    fun storedStringToObjects(data: String?): List<GenreModul>? {
        val gson = Gson()
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<GenreModul>?>() {}.type
        return gson.fromJson<List<GenreModul>>(data, listType)
    }

    @TypeConverter
    fun objectsToStoredString(myObjects: List<GenreModul?>?): String? {
        val gson = Gson()
        return gson.toJson(myObjects)
    }
}