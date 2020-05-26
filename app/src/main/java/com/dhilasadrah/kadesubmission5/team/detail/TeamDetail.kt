package com.dhilasadrah.kadesubmission5.team.detail

import com.google.gson.annotations.SerializedName

data class TeamDetail(
    @SerializedName("idTeam")
    var idTeam: String? = null,

    @SerializedName("strTeam")
    var teamName: String? = null,

    @SerializedName("intFormedYear")
    var formedYear: String? = null,

    @SerializedName("strTeamBadge")
    var teamBadge: String? = null,

    @SerializedName("strDescriptionEN")
    var description: String? = null,

    @SerializedName("strCountry")
    var country: String? = null,

    @SerializedName("strStadium")
    var stadium: String? = null,

    @SerializedName("strTeamBanner")
    var teamBanner: String? = null,

    @SerializedName("strTeamFanart1")
    var teamFanart: String? = null,

    @SerializedName("strStadiumLocation")
    var stadiumLocation: String? = null
)