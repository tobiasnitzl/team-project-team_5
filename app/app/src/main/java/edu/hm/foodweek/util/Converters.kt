package edu.hm.foodweek.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import edu.hm.foodweek.plans.persistence.model.Meal
import edu.hm.foodweek.recipes.persistence.model.IngredientAmount


class Converters {
    @TypeConverter
    fun fromMeals(value: List<Meal>) = Gson().toJson(value)

    @TypeConverter
    fun toMeals(value: String) = run {
        val itemType = object : TypeToken<List<Meal>>() {}.type
        Gson().fromJson<List<Meal>>(value, itemType)
    }

    @TypeConverter
    fun fromIngredientAmounts(value: List<IngredientAmount>): String = Gson().toJson(value)

    @TypeConverter
    fun toIngredientAmounts(value: String): List<IngredientAmount> = run {
        val itemType = object : TypeToken<List<IngredientAmount>>() {}.type
        Gson().fromJson(value, itemType)
    }

    // ################# List<String>
    @TypeConverter
    fun toStringList(value: String) = run {
        val itemType = object : TypeToken<List<String>>() {}.type
        Gson().fromJson<List<String>>(value, itemType)
    }

    @TypeConverter
    fun fromStringList(value: List<String>) = Gson().toJson(value)

    // ################# Set<String>
    @TypeConverter
    fun toStringSet(value: String) = run {
        val itemType = object : TypeToken<List<String>>() {}.type
        Gson().fromJson<List<String>>(value, itemType).toSet()
    }

    @TypeConverter
    fun fromStringSet(value: Set<String>) = Gson().toJson(value.toList())

    // ################# Map<Int,Long>
    @TypeConverter
    fun toIntLongMap(value: String) = run {
        val itemType = object : TypeToken<List<Pair<Int, Long>>>() {}.type
        val map: MutableMap<Int, Long> = mutableMapOf<Int,Long>()
        Gson().fromJson<List<Pair<Int, Long>>>(value, itemType).forEach { pair ->
            // cast to Int, because SQL-lite holds numbers as double values
            map[pair.first.toInt()] = pair.second.toLong()
        }
        map
    }

    @TypeConverter
    fun fromIntLongMap(value: Map<Int, Long>) = Gson().toJson(value.toList())


}