package com.noble.activity.artifactcards.model

import android.app.Application
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.graphics.Color
import android.support.v4.os.ConfigurationCompat
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.noble.activity.artifactcards.App
import com.noble.activity.artifactcards.R
import com.noble.activity.artifactcards.utils.*
import io.reactivex.annotations.NonNull

@Entity(tableName = "cards")
data class Card constructor(

    @SerializedName("card_id")
    @PrimaryKey
    var cardId: Int,

    @SerializedName("base_card_id")
    var baseCardId: Int,

    @SerializedName("card_type")
    var cardType: String,

    @SerializedName("card_name")
    var cardName: CardName,

    @SerializedName("card_text")
    var cardText: CardText,

    @SerializedName("mini_image")
    var miniImage: MiniImage,

    @SerializedName("large_image")
    var largeImage: LargeImage,

    @SerializedName("ingame_image")
    var ingameImage: IngameImage,

    @SerializedName("hit_points")
    var hitPoints: Int,

    @SerializedName("references")
    var references: List<Reference>,

    @SerializedName("illustrator")
    var illustrator: String?,

    @SerializedName("rarity")
    var rarity: String?,

    @SerializedName("item_def")
    var itemDef: Int?,

    @SerializedName("mana_cost")
    var manaCost: Int?,

    @SerializedName("attack")
    var attack: Int?,

    @SerializedName("sub_type")
    var subType: String?,

    @SerializedName("gold_cost")
    var goldCost: Int?,

    @SerializedName("is_black")
    var isBlack: Boolean? = false,

    @SerializedName("is_blue")
    var isBlue: Boolean? = false,

    @SerializedName("is_green")
    var isGreen: Boolean? = false,

    @SerializedName("is_red")
    var isRed: Boolean? = false
) {
    fun getColorName(): String {
        if (isBlack != null){
            return App.get()!!.getString(R.string.black_color)
        }
        if (isBlue != null) {
            return App.get()!!.getString(R.string.blue_color)
        }
        if (isGreen != null) {
            return  App.get()!!.getString(R.string.green_color)
        }
        if (isRed != null) {
            return App.get()!!.getString(R.string.red_color)
        }

        return ""
    }

    fun getTypeByLocale() = when(cardType) {
        HERO_CARD_TYPE -> App.get()!!.getString(R.string.hero_type)
        SPELL_CARD_TYPE -> App.get()!!.getString(R.string.spell_type)
        ABILITY_CARD_TYPE -> App.get()!!.getString(R.string.ability_type)
        PASSIVE_ABILITY_CARD_TYPE -> App.get()!!.getString(R.string.passive_ability_type)
        ITEM_CARD_TYPE -> App.get()!!.getString(R.string.item_type)
        IMPROVEMENT_CARD_TYPE -> App.get()!!.getString(R.string.improvement_type)
        CREEP_CARD_TYPE -> App.get()!!.getString(R.string.creep_type)
        else -> ""
    }

    fun getRarityByLocale() = when(rarity) {
        RARITY_COMMON -> App.get()!!.getString(R.string.common_rarity)
        RARITY_UNCOMMON -> App.get()!!.getString(R.string.uncommon_rarity)
        RARITY_RARE -> App.get()!!.getString(R.string.rare_rarity)
        else -> ""
    }


    fun getTextColor(): Int {
        if (isBlack != null){
            return Color.parseColor("#000000")
        }
        if (isBlue != null) {
            return Color.parseColor("#448AFF")
        }
        if (isGreen != null) {
            return  Color.parseColor("#2E7D32")
        }
        if (isRed != null) {
            return Color.parseColor("#FF0000")
        }

        return Color.parseColor("#000000")
    }

    fun getNameByLocale(locale: String) = when(locale) {
        LOCALE_RU -> cardName.russian
        LOCALE_EN -> cardName.english
        else -> cardName.english
    }

    fun getTextByLocale(locale: String) = when(locale) {
        LOCALE_RU -> cardText.russian
        LOCALE_EN -> cardText.english
        else -> cardText.english
    }

    fun getLargeImageByLocale(locale: String) = when(locale) {
        LOCALE_RU -> largeImage.russian
        LOCALE_EN -> largeImage.default
        else -> largeImage.default
    }

    fun getMiniImageByLocale() = miniImage.default

}