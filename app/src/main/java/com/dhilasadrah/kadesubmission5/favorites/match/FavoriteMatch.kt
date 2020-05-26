package com.dhilasadrah.kadesubmission5.favorites.match

data class FavoriteMatch(val id: Long?, val idMatch: String?, val matchDate: String?, val matchTime: String?,
                         val idHomeTeam: String?, val homeTeamName: String?, val homeTeamScore: String?,
                         val idAwayTeam: String?, val awayTeamName: String?, val awayTeamScore: String?, val status: String?
){
    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val MATCH_ID: String = "MATCH_ID"
        const val MATCH_DATE: String = "MATCH_DATE"
        const val MATCH_TIME: String = "MATCH_TIME"
        const val HOME_TEAM_ID: String = "HOME_TEAM_ID"
        const val HOME_TEAM_NAME: String = "HOME_TEAM_NAME"
        const val HOME_TEAM_SCORE: String = "HOME_TEAM_SCORE"
        const val AWAY_TEAM_ID: String = "AWAY_TEAM_ID"
        const val AWAY_TEAM_NAME: String = "AWAY_TEAM_NAME"
        const val AWAY_TEAM_SCORE: String = "AWAY_TEAM_SCORE"
        const val STATUS: String = "STATUS"
    }
}