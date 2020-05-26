package com.dhilasadrah.kadesubmission5.league.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.dhilasadrah.kadesubmission5.R
import com.dhilasadrah.kadesubmission5.api.ApiRepository
import com.dhilasadrah.kadesubmission5.util.invisible
import com.dhilasadrah.kadesubmission5.util.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.fragment_info_league.*

class LeagueDetailFragment : Fragment(), LeagueDetailView {

    companion object {
        const val LEAGUE_ID = "league_id"
        const val LEAGUE_NAME = "league_name"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_info_league, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val idLeague = activity?.intent?.getStringExtra(LEAGUE_ID)
        val leagueName = activity?.intent?.getStringExtra(LEAGUE_NAME)
        if (idLeague != null) {
            val presenter = LeagueDetailPresenter(this, ApiRepository(),Gson())
            presenter.getLeagueDetail(idLeague)
        }

        val actionBar: ActionBar? = (activity as AppCompatActivity?)!!.supportActionBar
        actionBar?.title = leagueName
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun showLoading() {
        progressBarDetail.visible()
        imgEmpty.visible()
    }

    override fun hideLoading() {
        progressBarDetail.invisible()
        imgEmpty.invisible()
    }

    override fun showLeagueDetail(data: LeagueDetail) {
        Picasso.get().load(data.leagueFanart).into(league_banner)
        Picasso.get().load(data.leaguePoster).into(league_badge)
        league_name.text = data.leagueName
        league_formedYear.text = data.formedYear
        league_country.text = data.country
        league_description.text = data.description
    }
}