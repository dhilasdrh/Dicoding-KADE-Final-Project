package com.dhilasadrah.kadesubmission5.team

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dhilasadrah.kadesubmission5.R
import com.dhilasadrah.kadesubmission5.team.detail.TeamDetailActivity
import com.dhilasadrah.kadesubmission5.team.detail.TeamDetailActivity.Companion.TEAM_ID
import com.dhilasadrah.kadesubmission5.team.detail.TeamDetailActivity.Companion.TEAM_NAME
import com.dhilasadrah.kadesubmission5.team.search.TeamSearchActivity
import com.dhilasadrah.kadesubmission5.api.ApiRepository
import com.dhilasadrah.kadesubmission5.league.detail.LeagueDetailFragment.Companion.LEAGUE_ID
import com.dhilasadrah.kadesubmission5.util.invisible
import com.dhilasadrah.kadesubmission5.util.visible
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.startActivity

class TeamFragment : Fragment(), TeamView, AnkoComponent<Context> {
    private var team: MutableList<Team> = mutableListOf()
    private lateinit var presenter: TeamPresenter
    private lateinit var adapter: TeamListAdapter
    private lateinit var rvTeamList: RecyclerView
    private lateinit var pbTeamList: ProgressBar
    private lateinit var idLeague: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return createView(AnkoContext.create(requireContext()))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        idLeague = activity?.intent?.getStringExtra(LEAGUE_ID).toString()

        presenter = TeamPresenter(this, ApiRepository(), Gson())
        presenter.getTeamList(idLeague)
        adapter = TeamListAdapter(team) {
            startActivity<TeamDetailActivity>(
                TEAM_ID to it.IdTeam,
                TEAM_NAME to it.teamName
            )
        }
        rvTeamList.layoutManager = GridLayoutManager(context, 3)
        rvTeamList.adapter = adapter

        val actionBar: ActionBar? = (activity as AppCompatActivity?)!!.supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

   override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.search_menu -> {
                context?.startActivity<TeamSearchActivity>()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showLoading() {
        pbTeamList.visible()
    }

    override fun hideLoading() {
        pbTeamList.invisible()
    }

    override fun showTeamList(data: List<Team>) {
        team.clear()
        team.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        relativeLayout {
            lparams(matchParent, matchParent)

            rvTeamList = recyclerView {
                id = R.id.rvTeamList
                lparams(matchParent, wrapContent)
            }

            pbTeamList = progressBar {
                id = R.id.pbTeamList
            }.lparams(matchParent, wrapContent) {
                centerInParent()
            }
        }
    }
}