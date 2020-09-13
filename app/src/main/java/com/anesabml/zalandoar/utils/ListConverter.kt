package com.anesabml.zalandoar.utils

import androidx.room.TypeConverter

class ListConverter {

    @TypeConverter
    fun fromString(stringListString: String?): List<String>? =
        stringListString?.split(",")

    @TypeConverter
    fun listToString(stringList: List<String>?): String? =
        stringList?.joinToString(separator = ",")
}