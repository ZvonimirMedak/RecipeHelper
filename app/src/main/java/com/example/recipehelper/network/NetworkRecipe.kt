package com.example.recipehelper.network
import android.annotation.SuppressLint
import android.os.Parcelable

import kotlinx.android.parcel.Parcelize
import java.math.RoundingMode
import java.text.DecimalFormat


@SuppressLint("ParcelCreator")
@Parcelize
data class NetworkRecipe(
    val count: Int,
    val from: Int,
    val hits: List<Hit>,
    val more: Boolean,
    val q: String,
    val to: Int
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class Hit(
    val bookmarked: Boolean,
    val bought: Boolean,
    val recipe: Recipe
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class Recipe(
    val calories: Double,
    val cautions: List<String>,
    val dietLabels: List<String>,
    val digest: List<Digest>,
    val healthLabels: List<String>,
    val image: String,
    val ingredientLines: List<String>,
    val ingredients: List<Ingredient>,
    val label: String,
    val shareAs: String,
    val source: String,
    val totalDaily: TotalDaily,
    val totalNutrients: TotalNutrients,
    val totalTime: Double,
    val totalWeight: Double,
    val uri: String,
    val url: String,
    val yield: Double
) : Parcelable{
    fun changeToString() : String {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        return df.format(calories).toString() + "kcal"
    }
}

@SuppressLint("ParcelCreator")
@Parcelize
data class Digest(
    val daily: Double,
    val hasRDI: Boolean,
    val label: String,
    val schemaOrgTag: String?,
    val sub: List<Sub>,
    val tag: String,
    val total: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class Sub(
    val daily: Double,
    val hasRDI: Boolean,
    val label: String,
    val schemaOrgTag: String?,
    val tag: String,
    val total: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class Ingredient(
    val text: String,
    val weight: Double
) : Parcelable{
    fun formatWeight() = weight.toString()
}

@SuppressLint("ParcelCreator")
@Parcelize
data class TotalDaily(
    val CA: CA,
    val CHOCDF: CHOCDF,
    val CHOLE: CHOLE,
    val ENERC_KCAL: ENERCKCAL,
    val FASAT: FASAT,
    val FAT: FAT,
    val FE: FE,
    val FIBTG: FIBTG,
    val FOLDFE: FOLDFE,
    val K: K,
    val MG: MG,
    val NA: NA,
    val NIA: NIA,
    val P: P,
    val PROCNT: PROCNT,
    val RIBF: RIBF,
    val THIA: THIA,
    val TOCPHA: TOCPHA,
    val VITA_RAE: VITARAE,
    val VITB12: VITB12,
    val VITB6A: VITB6A,
    val VITC: VITC,
    val VITD: VITD,
    val VITK1: VITK1,
    val ZN: ZN
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class CA(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class CHOCDF(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class CHOLE(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class ENERCKCAL(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class FASAT(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class FAT(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class FE(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class FIBTG(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class FOLDFE(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class K(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class MG(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class NA(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class NIA(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class P(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class PROCNT(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class RIBF(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class THIA(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class TOCPHA(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class VITARAE(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class VITB12(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class VITB6A(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class VITC(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class VITD(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class VITK1(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class ZN(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class TotalNutrients(
    val CA: CAX,
    val CHOCDF: CHOCDFX,
    val CHOLE: CHOLEX,
    val ENERC_KCAL: ENERCKCALX,
    val FAMS: FAMS,
    val FAPU: FAPU,
    val FASAT: FASATX,
    val FAT: FATX,
    val FATRN: FATRN,
    val FE: FEX,
    val FIBTG: FIBTGX,
    val FOLDFE: FOLDFEX,
    val FOLFD: FOLFD,
    val K: KX,
    val MG: MGX,
    val NA: NAX,
    val NIA: NIAX,
    val P: PX,
    val PROCNT: PROCNTX,
    val RIBF: RIBFX,
    val SUGAR: SUGAR,
    val THIA: THIAX,
    val TOCPHA: TOCPHAX,
    val VITA_RAE: VITARAEX,
    val VITB12: VITB12X,
    val VITB6A: VITB6AX,
    val VITC: VITCX,
    val VITD: VITDX,
    val VITK1: VITK1X,
    val WATER: WATER,
    val ZN: ZNX
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class CAX(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class CHOCDFX(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class CHOLEX(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class ENERCKCALX(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class FAMS(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class FAPU(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class FASATX(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class FATX(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class FATRN(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class FEX(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class FIBTGX(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class FOLDFEX(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class FOLFD(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class KX(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class MGX(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class NAX(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class NIAX(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class PX(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class PROCNTX(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class RIBFX(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class SUGAR(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class THIAX(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class TOCPHAX(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class VITARAEX(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class VITB12X(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class VITB6AX(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class VITCX(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class VITDX(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class VITK1X(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class WATER(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class ZNX(
    val label: String,
    val quantity: Double,
    val unit: String
) : Parcelable