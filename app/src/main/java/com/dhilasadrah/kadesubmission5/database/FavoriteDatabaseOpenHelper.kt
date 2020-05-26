package com.dhilasadrah.kadesubmission5.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.dhilasadrah.kadesubmission5.favorites.match.FavoriteMatch
import com.dhilasadrah.kadesubmission5.favorites.team.FavoriteTeam
import org.jetbrains.anko.db.*

class FavoriteDatabaseOpenHelper(context: Context) : ManagedSQLiteOpenHelper(context, "Favorite.db", null, 1) {
    companion object {
        private var instance: FavoriteDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(context: Context): FavoriteDatabaseOpenHelper {
            if (instance == null) {
                instance = FavoriteDatabaseOpenHelper(context.applicationContext)
            }
            return instance as FavoriteDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
            FavoriteMatch.TABLE_FAVORITE, true,
            FavoriteMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteMatch.MATCH_ID to TEXT + UNIQUE,
            FavoriteMatch.MATCH_DATE to TEXT,
            FavoriteMatch.MATCH_TIME to TEXT,
            FavoriteMatch.HOME_TEAM_ID to TEXT,
            FavoriteMatch.HOME_TEAM_NAME to TEXT,
            FavoriteMatch.HOME_TEAM_SCORE to TEXT,
            FavoriteMatch.AWAY_TEAM_ID to TEXT,
            FavoriteMatch.AWAY_TEAM_NAME to TEXT,
            FavoriteMatch.AWAY_TEAM_SCORE to TEXT,
            FavoriteMatch.STATUS to TEXT)

        db.createTable(
            FavoriteTeam.TABLE_FAVORITE_TEAM, true,
            FavoriteTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteTeam.TEAM_ID to TEXT + UNIQUE,
            FavoriteTeam.TEAM_BADGE to TEXT,
            FavoriteTeam.TEAM_NAME to TEXT,
            FavoriteTeam.TEAM_DESCRIPTION to TEXT,
            FavoriteTeam.TEAM_BANNER to TEXT,
            FavoriteTeam.FORMED_YEAR to TEXT,
            FavoriteTeam.STADIUM to TEXT,
            FavoriteTeam.COUNTRY to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(FavoriteMatch.TABLE_FAVORITE, true)
        db.dropTable(FavoriteTeam.TABLE_FAVORITE_TEAM, true)
    }
}

val Context.database: FavoriteDatabaseOpenHelper
    get() = FavoriteDatabaseOpenHelper.getInstance(applicationContext)