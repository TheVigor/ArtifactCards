package com.noble.activity.artifactcards.model.card

import com.google.gson.annotations.SerializedName

data class SetInfo constructor(
    @SerializedName("set_id")
    var setId: Int,

    @SerializedName("pack_item_def")
    var packItemDef: Int,

    @SerializedName("name")
    var name: Name

)