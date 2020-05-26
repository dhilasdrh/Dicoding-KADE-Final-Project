package com.dhilasadrah.kadesubmission5.league.detail

import com.google.gson.annotations.SerializedName

data class LeagueDetail(
    @SerializedName("idLeague")
    var idLeague: String? = null,

    @SerializedName("strLeague")
    var leagueName: String? = null,

    @SerializedName("strBadge")
    var leagueBadge: String? = null,

    @SerializedName("intFormedYear")
    var formedYear: String? = null,

    @SerializedName("strCountry")
    var country: String? = null,

    @SerializedName("strDescriptionEN")
    var description: String? = null,

    @SerializedName("strBanner")
    var leagueBanner: String? = null,

    @SerializedName("strPoster")
    var leaguePoster: String? = null,

    @SerializedName("strFanart1")
    var leagueFanart: String? = null,

    @SerializedName("strWebsite")
    var leagueWebsite: String? = null
)