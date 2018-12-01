package com.noble.activity.artifactcards.model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.reactivex.annotations.NonNull

@Entity(tableName = "cards")
data class Card constructor(

    @SerializedName("card_id")
    @PrimaryKey
    @Expose
    var cardId: Int,

    @SerializedName("base_card_id")
    @Expose
    var baseCardId: Int,

    @SerializedName("card_type")
    @Expose
    var cardType: String,

    @SerializedName("card_name")
    @Expose
    @Embedded(prefix = "card_name")
    var cardName: CardName,

    @SerializedName("card_text")
    @Expose
    @Embedded(prefix = "card_text")
    var cardText: CardText? = null,

    @SerializedName("mini_image")
    @Expose
    @Embedded(prefix = "mini_image")
    var miniImage: MiniImage? = null,

    @SerializedName("large_image")
    @Expose
    @Embedded(prefix = "large_image")
    var largeImage: LargeImage? = null,

    @SerializedName("ingame_image")
    @Expose
    @Embedded(prefix = "ingame_image")
    var ingameImage: IngameImage? = null,

    @SerializedName("hit_points")
    @Expose
    var hitPoints: Int? = null,

    @SerializedName("references")
    @Expose
    var references: List<Reference>,

    @SerializedName("illustrator")
    @Expose
    var illustrator: String? = null,

    @SerializedName("rarity")
    @Expose
    var rarity: String? = null,

    @SerializedName("item_def")
    @Expose
    var itemDef: Int? = null,

    @SerializedName("mana_cost")
    @Expose
    var manaCost: Int? = null,

    @SerializedName("attack")
    @Expose
    var attack: Int? = null,

    @SerializedName("sub_type")
    @Expose
    var subType: String? = null,

    @SerializedName("gold_cost")
    @Expose
    var goldCost: Int? = null,

    @SerializedName("is_black")
    @Expose
    var isBlack: Boolean? = false,

    @SerializedName("is_blue")
    @Expose
    var isBlue: Boolean? = false,

    @SerializedName("is_green")
    @Expose
    var isGreen: Boolean? = false,

    @SerializedName("is_red")
    @Expose
    var isRed: Boolean? = false
)