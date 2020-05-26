package com.dhilasadrah.kadesubmission5.match.detail

import com.google.gson.annotations.SerializedName

data class MatchDetail (
    @SerializedName("idEvent")
    var eventId: String? = null,

    @SerializedName("strDate")
    var eventDate: String? = null,

    @SerializedName("strTime")
    var eventTime: String? = null,

    @SerializedName("idHomeTeam")
    var homeTeamId: String? = null,

    @SerializedName("strHomeTeam")
    var homeTeam: String? = null,

    @SerializedName("intHomeScore")
    var homeScore: String? = null,

    @SerializedName("strHomeGoalDetails")
    var homeGoalDetails: String? = null,

    @SerializedName("strHomeRedCards")
    var homeYellowCards: String? = null,

    @SerializedName("strHomeYellowCards")
    var homeRedCards: String? = null,

    @SerializedName("strHomeLineupGoalkeeper")
    var homeGoalKeeper: String? = null,

    @SerializedName("strHomeLineupDefense")
    var homeDefense: String? = null,

    @SerializedName("strHomeLineupMidfield")
    var homeMidfield: String? = null,

    @SerializedName("strHomeLineupForward")
    var homeForward: String? = null,

    @SerializedName("strHomeLineupSubstitutes")
    var homeSubstitutes: String? = null,


    @SerializedName("idAwayTeam")
    var awayTeamId: String? = null,

    @SerializedName("strAwayTeam")
    var awayTeam: String? = null,

    @SerializedName("intAwayScore")
    var awayScore: String? = null,

    @SerializedName("strAwayGoalDetails")
    var awayGoalDetails: String? = null,

    @SerializedName("strHAwayRedCards")
    var awayYellowCards: String? = null,

    @SerializedName("strAwayYellowCards")
    var awayRedCards: String? = null,

    @SerializedName("strAwayLineupGoalkeeper")
    var awayGoalKeeper: String? = null,

    @SerializedName("strAwayLineupDefense")
    var awayDefense: String? = null,

    @SerializedName("strAwayLineupMidfield")
    var awayMidfield: String? = null,

    @SerializedName("strAwayLineupForward")
    var awayForward: String? = null,

    @SerializedName("strAwayLineupSubstitutes")
    var awaySubstitutes: String? = null
)