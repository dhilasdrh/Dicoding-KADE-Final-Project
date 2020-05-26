package com.dhilasadrah.kadesubmission5.favorites.team

data class FavoriteTeam(
    val id: Long?, val idTeam: String?, val teamBadge: String?, val teamName: String?, val teamDesc: String?,
    val teamBanner: String?, val formedYear: String?, val stadium: String?, val country: String?
) {
    companion object {
        const val TABLE_FAVORITE_TEAM: String = "TABLE_FAVORITE_TEAM"
        const val ID: String = "ID_"
        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_BADGE: String = "TEAM_BADGE"
        const val TEAM_NAME: String = "TEAM_NAME"
        const val TEAM_DESCRIPTION: String = "TEAM_DESCRIPTION"
        const val TEAM_BANNER: String = "TEAM_BANNER"
        const val FORMED_YEAR: String = "TEAM_FORMED_YEAR"
        const val STADIUM: String = "STADIUM"
        const val COUNTRY: String = "COUNTRY"
    }
}