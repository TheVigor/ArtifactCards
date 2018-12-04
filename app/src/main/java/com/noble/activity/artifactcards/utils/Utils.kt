package com.noble.activity.artifactcards.utils

import android.content.Context
import android.widget.Toast


val HERO_CARD_TYPE = "Hero"
val SPELL_CARD_TYPE = "Spell"
val ABILITY_CARD_TYPE = "Ability"
val PASSIVE_ABILITY_CARD_TYPE = "Passive Ability"
val ITEM_CARD_TYPE = "Item"
val IMPROVEMENT_CARD_TYPE = "Improvement"
val CREEP_CARD_TYPE = "Creep"

fun Context.showToast(text: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, text, duration).show()
}
