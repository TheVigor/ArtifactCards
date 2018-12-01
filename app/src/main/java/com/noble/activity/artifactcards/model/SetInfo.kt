package com.noble.activity.artifactcards.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SetInfo constructor(
    @SerializedName("set_id")
    var setId: Int,

    @SerializedName("pack_item_def")
    var packItemDef: Int,

    @SerializedName("name")
    var name: Name

)