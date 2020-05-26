package com.dhilasadrah.kadesubmission5.api

import com.dhilasadrah.kadesubmission5.BuildConfig

object TheSportDBApi {

    private var url: String = BuildConfig.BASE_URL + "api/v1/json/" + BuildConfig.TSDB_API_KEY + "/"

    fun getLeagueDetail(idLeague: String?): String? {
        return url + "lookupleague.php?id=$idLeague"
    }

    fun getPastMatch(idLeague: String?): String? {
        return url + "eventspastleague.php?id=$idLeague"
    }

    fun getNextMatch(idLeague: String?): String? {
        return url + "eventsnextleague.php?id=$idLeague"
    }

    fun getMatchDetail(idLeague: String?): String? {
        return url + "lookupevent.php?id=$idLeague"
    }

    fun getMatchSearch(query: String?): String? {
        return url + "searchevents.php?e=$query"
    }

    fun getAllTeams(idLeague: String?): String? {
        return url + "lookup_all_teams.php?id=$idLeague"
    }

    fun getTeamDetail(idTeam: String?): String? {
        return url + "lookupteam.php?id=$idTeam"
    }

    fun getTeamSearch(query: String?): String? {
        return url + "searchteams.php?t=$query"
    }

    fun getStandings(idLeague: String?): String? {
        return url + "lookuptable.php?l=$idLeague"
    }
}