package com.noble.activity.artifactcards.utils

import android.content.Context
import android.os.Build
import android.text.Html
import android.widget.TextView
import android.widget.Toast


val HERO_CARD_TYPE = "Hero"
val SPELL_CARD_TYPE = "Spell"
val ABILITY_CARD_TYPE = "Ability"
val PASSIVE_ABILITY_CARD_TYPE = "Passive Ability"
val ITEM_CARD_TYPE = "Item"
val IMPROVEMENT_CARD_TYPE = "Improvement"
val CREEP_CARD_TYPE = "Creep"

val SEP = ";;;"

val LOCALE_RU = "ru"
val LOCALE_EN = "en"

val RARITY_COMMON = "Common"
val RARITY_UNCOMMON = "Uncommon"
val RARITY_RARE = "Rare"

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
