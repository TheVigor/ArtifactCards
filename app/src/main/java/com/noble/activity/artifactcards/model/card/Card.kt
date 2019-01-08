package com.noble.activity.artifactcards.model.card

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.graphics.Color
import com.google.gson.annotations.SerializedName
import com.noble.activity.artifactcards.R
import com.noble.activity.artifactcards.app.app
import com.noble.activity.artifactcards.app.colorFilter
import com.noble.activity.artifactcards.utils.*

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

    @SerializedName("armor")
    var armor: Int?,

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
    fun getColorNameWithTr(): String {
        if (isBlack != null){
            return app.getString(R.string.black_color)
        }
        if (isBlue != null) {
            return app.getString(R.string.blue_color)
        }
        if (isGreen != null) {
            return  app.getString(R.string.green_color)
        }
        if (isRed != null) {
            return app.getString(R.string.red_color)
        }

        return ""
    }

    fun getColorName(): String {
        if (isBlack != null){
            return COLOR_BLACK
        }
        if (isBlue != null) {
            return COLOR_BLUE
        }
        if (isGreen != null) {
            return  COLOR_GREEN
        }
        if (isRed != null) {
            return COLOR_RED
        }

        return ""
    }

    fun getColorByCardType() = when(cardType) {
        HERO_CARD_TYPE -> Color.parseColor("#448AFF")
        SPELL_CARD_TYPE -> Color.parseColor("#448AFF")
        ABILITY_CARD_TYPE -> Color.parseColor("#448AFF")
        PASSIVE_ABILITY_CARD_TYPE -> Color.parseColor("#448AFF")
        ITEM_CARD_TYPE -> Color.parseColor("#FFDF00")
        IMPROVEMENT_CARD_TYPE -> Color.parseColor("#448AFF")
        CREEP_CARD_TYPE -> Color.parseColor("#448AFF")
        else -> Color.parseColor("#448AFF")
    }


    fun getTypeByLocale() = when(cardType) {
        HERO_CARD_TYPE -> app.getString(R.string.hero_type)
        SPELL_CARD_TYPE -> app.getString(R.string.spell_type)
        ABILITY_CARD_TYPE -> app.getString(R.string.ability_type)
        PASSIVE_ABILITY_CARD_TYPE -> app.getString(R.string.passive_ability_type)
        ITEM_CARD_TYPE -> app.getString(R.string.item_type)
        IMPROVEMENT_CARD_TYPE -> app.getString(R.string.improvement_type)
        CREEP_CARD_TYPE -> app.getString(R.string.creep_type)
        else -> ""
    }

    fun getTypeIcon() = when(cardType) {
        HERO_CARD_TYPE -> app.getDrawable(R.drawable.ic_hero)
        SPELL_CARD_TYPE -> app.getDrawable(R.drawable.ic_spell)
        ABILITY_CARD_TYPE -> app.getDrawable(R.drawable.ic_spell)
        PASSIVE_ABILITY_CARD_TYPE -> app.getDrawable(R.drawable.ic_spell)
        ITEM_CARD_TYPE -> app.getDrawable(R.drawable.ic_item)
        IMPROVEMENT_CARD_TYPE -> app.getDrawable(R.drawable.ic_improvement)
        CREEP_CARD_TYPE -> app.getDrawable(R.drawable.ic_creep)
        else -> app.getDrawable(R.drawable.ic_spell)
    }


    fun getRarityIcon() = when(rarity) {
        RARITY_COMMON -> app.getDrawable(R.drawable.ic_rarity_common)
        RARITY_UNCOMMON -> app.getDrawable(R.drawable.ic_rarity_uncommon)
        RARITY_RARE -> app.getDrawable(R.drawable.ic_rarity_rare)
        else -> app.getDrawable(R.drawable.ic_rarity_common)
    }


    fun getRarityByLocale() = when(rarity) {
        RARITY_COMMON -> app.getString(R.string.common_rarity)
        RARITY_UNCOMMON -> app.getString(R.string.uncommon_rarity)
        RARITY_RARE -> app.getString(R.string.rare_rarity)
        else -> when (cardType) {
            HERO_CARD_TYPE -> app.getString(R.string.card_basic)
            SPELL_CARD_TYPE -> app.getString(R.string.card_sign)
            ITEM_CARD_TYPE -> app.getString(R.string.card_basic)
            IMPROVEMENT_CARD_TYPE -> app.getString(R.string.card_sign)
            CREEP_CARD_TYPE -> app.getString(R.string.card_summon)
            else -> app.getString(R.string.card_basic)
        }
    }

    fun getCardAttack(): String {
        return if (attack != null) attack.toString() else "0"
    }

    fun getCardArmor(): String {
        return if (armor != null) armor.toString() else "0"
    }


    fun getCardHealth(): String {
        return if (hitPoints != null) hitPoints.toString() else "0"
    }

    fun getMana(): String {
        return if (manaCost != null) manaCost.toString() else "0"
    }

    fun getGold(): CharSequence? {
        return if (goldCost != null) goldCost.toString() else "0"
    }

    fun getManaOrGold(): String {
        if (manaCost != null) {
            return manaCost.toString()
        }

        if (goldCost != null) {
            return goldCost.toString()
        }

        return ""
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

    fun isMatchQuery(query: String): Boolean {
        if (this.cardName.english == null) return false
        if (this.cardName.russian == null) return false

        return this.cardName.english!!.contains(query, true)
                || this.cardName.russian!!.contains(query, true)

    }

    fun isMatchColor(): Boolean {
        if (this.cardType == ITEM_CARD_TYPE) {
                return true
        }

        return colorFilter.contains(this.getColorName())
    }

}