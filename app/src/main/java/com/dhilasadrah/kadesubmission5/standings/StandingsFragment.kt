package com.dhilasadrah.kadesubmission5.standings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dhilasadrah.kadesubmission5.R
import com.dhilasadrah.kadesubmission5.api.ApiRepository
import com.dhilasadrah.kadesubmission5.league.detail.LeagueDetailFragment.Companion.LEAGUE_ID
import com.dhilasadrah.kadesubmission5.util.invisible
import com.dhilasadrah.kadesubmission5.util.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_standings.*

class StandingsFragment : Fragment(), StandingsView {
    private lateinit var presenter: StandingsPresenter
    private lateinit var adapter: StandingsAdapter
    private var standings: MutableList<Standings> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_standings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val idLeague = activity?.intent?.getStringExtra(LEAGUE_ID)

        presenter = StandingsPresenter(this, ApiRepository(), Gson())
        presenter.getStandingsList(idLeague)

        adapter = StandingsAdapter(standings)
        rvStandings.layoutManager = LinearLayoutManager(context)
        rvStandings.adapter = adapter

        val actionBar: ActionBar? = (activity as AppCompatActivity?)!!.supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun showLoading() {
        pbStandings.visible()
    }

    override fun hideLoading() {
        pbStandings.invisible()
    }

    override fun showStandingsList(data: List<Standings>) {
        standings.clear()
        standings.addAll(data)
        adapter.notifyDataSetChanged()
    }

}
