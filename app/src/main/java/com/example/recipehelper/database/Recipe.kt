package com.example.recipehelper.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.math.RoundingMode
import java.text.DecimalFormat
@Parcelize
@Entity(tableName = "saved_recipes")
data class DatabaseRecipe (
    @PrimaryKey
    val source: String,
    val calories : Double,
    val recipeName: String,
    val url : String,
    val timeToMake : Double,
    val image : String
): Parcelable{fun changeToString() : String {
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.CEILING
    return df.format(calories).toString() + "kcal"
}
}
