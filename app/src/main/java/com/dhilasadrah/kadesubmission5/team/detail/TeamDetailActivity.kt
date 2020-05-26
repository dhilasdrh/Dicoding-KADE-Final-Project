package com.dhilasadrah.kadesubmission5.team.detail

import android.database.sqlite.SQLiteConstraintException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.dhilasadrah.kadesubmission5.R
import com.dhilasadrah.kadesubmission5.api.ApiRepository
import com.dhilasadrah.kadesubmission5.database.database
import com.dhilasadrah.kadesubmission5.favorites.team.FavoriteTeam
import com.dhilasadrah.kadesubmission5.util.invisible
import com.dhilasadrah.kadesubmission5.util.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class TeamDetailActivity : AppCompatActivity(), TeamDetailView {
    private lateinit var team: TeamDetail
    private lateinit var teamId: String
    private lateinit var teamName: String
    private lateinit var presenter: TeamDetailPresenter
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    companion object {
        const val TEAM_ID = "team_id"
        const val TEAM_NAME = "team_nama"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        teamId = intent?.getStringExtra(TEAM_ID).toString()
        teamName = intent?.getStringExtra(TEAM_NAME).toString()

        presenter = TeamDetailPresenter(this, ApiRepository(),Gson())
        presenter.getTeamDetail(teamId)

        favoriteState()
        supportActionBar?.title = teamName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun favoriteState() {
        database.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
                .whereArgs("(TEAM_ID = {id})", "id" to teamId)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            if (favorite.isNotEmpty()) isFavorite = true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favorite, menu)
        menuItem = menu
        setFavorite()
        return super.onCreateOptionsMenu(menu)
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_fav_fill)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_fav_outline)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_fav -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
                true
            }
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(
                    FavoriteTeam.TABLE_FAVORITE_TEAM,
                    FavoriteTeam.TEAM_ID to team.idTeam,
                    FavoriteTeam.TEAM_BADGE to team.teamBadge,
                    FavoriteTeam.TEAM_NAME to team.teamName,
                    FavoriteTeam.TEAM_DESCRIPTION to team.description,
                    FavoriteTeam.TEAM_BANNER to team.teamFanart,
                    FavoriteTeam.FORMED_YEAR to team.formedYear,
                    FavoriteTeam.STADIUM to team.stadium,
                    FavoriteTeam.COUNTRY to team.country
                )
            }
            Toast.makeText(this, "Added to favorite", Toast.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException) {
            Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(
                    FavoriteTeam.TABLE_FAVORITE_TEAM, "(TEAM_ID = {id})",
                    "id" to teamId
                )
            }
            Toast.makeText(this, "Removed from favorite", Toast.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException) {
            Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

    override fun showLoading() {
        pbTeamDetail.visible()
    }

    override fun hideLoading() {
        pbTeamDetail.invisible()
    }

    override fun showTeamDetail(data: TeamDetail) {
        emptyTeam.invisible()
        team = data

        detail_teamName.text = data.teamName
        team_formedYear.text = data.formedYear
        team_country.text = data.country
        team_stadium.text = data.stadium
        team_description.text = data.description

        Picasso.get().load(data.teamBadge).into(detail_teamBadge)
        Picasso.get().load(data.teamBanner).into(teamBanner)
    }
}
