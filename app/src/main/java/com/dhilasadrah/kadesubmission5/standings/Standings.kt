package com.dhilasadrah.kadesubmission5.standings

import com.google.gson.annotations.SerializedName

data class Standings(
    @SerializedName("name")
    var teamName: String? = null,

    @SerializedName("played")
    var played: String? = null,

    @SerializedName("win")
    var win: String? = null,

    @SerializedName("draw")
    var draw: String? = null,

    @SerializedName("loss")
    var loss: String? = null,

    @SerializedName("total")
    var total: String? = null
)