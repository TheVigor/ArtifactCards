package com.noble.activity.artifactcards.utils

import android.content.Context
import android.os.Build
import android.text.Html
import android.widget.TextView
import android.widget.Toast


const val HERO_CARD_TYPE = "Hero"
const val SPELL_CARD_TYPE = "Spell"
const val ABILITY_CARD_TYPE = "Ability"
const val PASSIVE_ABILITY_CARD_TYPE = "Passive Ability"
const val ITEM_CARD_TYPE = "Item"
const val IMPROVEMENT_CARD_TYPE = "Improvement"
const val CREEP_CARD_TYPE = "Creep"

const val SEP = ";;;"

const val LOCALE_RU = "ru"
const val LOCALE_EN = "en"

const val RARITY_COMMON = "Common"
const val RARITY_UNCOMMON = "Uncommon"
const val RARITY_RARE = "Rare"

fun Context.showToast(text: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, text, duration).show()
}

fun TextView.setTextFromHtml(htmlText: String?) {
    if (!htmlText.isNullOrEmpty()) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            this.text = Html.fromHtml(htmlText, Html.FROM_HTML_MODE_COMPACT)
        } else {
            this.text = Html.fromHtml(htmlText)
        }
    }
}
