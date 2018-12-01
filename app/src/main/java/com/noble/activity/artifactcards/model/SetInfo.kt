package com.noble.activity.artifactcards.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SetInfo constructor(
    @SerializedName("set_id")
    @Expose
    var setId: Int,

    @SerializedName("pack_item_def")
    @Expose
    var packItemDef: Int,

    @SerializedName("name")
    @Expose
    var name: Name

)