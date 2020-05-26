package com.dhilasadrah.kadesubmission5.team.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.dhilasadrah.kadesubmission5.R
import com.dhilasadrah.kadesubmission5.team.detail.TeamDetailActivity.Companion.TEAM_ID
import com.dhilasadrah.kadesubmission5.team.detail.TeamDetailActivity.Companion.TEAM_NAME
import com.dhilasadrah.kadesubmission5.team.TeamListAdapter
import com.dhilasadrah.kadesubmission5.api.ApiRepository
import com.dhilasadrah.kadesubmission5.team.Team
import com.dhilasadrah.kadesubmission5.team.detail.TeamDetailActivity
import com.dhilasadrah.kadesubmission5.util.invisible
import com.dhilasadrah.kadesubmission5.util.replaceSpace
import com.dhilasadrah.kadesubmission5.util.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_team_search.*
import org.jetbrains.anko.startActivity

class TeamSearchActivity : AppCompatActivity(),
    TeamSearchView, MenuItem.OnActionExpandListener {
    private var team: MutableList<Team> = mutableListOf()
    private lateinit var presenter: TeamSearchPresenter
    private lateinit var adapter: TeamListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_search)

        presenter = TeamSearchPresenter(this, ApiRepository(), Gson())
        adapter = TeamListAdapter(team) {
            startActivity<TeamDetailActivity>(
                TEAM_ID to it.IdTeam,
                TEAM_NAME to it.teamName
            )
        }
        rvTeamSearch.layoutManager = GridLayoutManager(this, 3)
        rvTeamSearch.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchItem = menu?.findItem(R.id.searchView)
        val searchView = searchItem?.actionView as SearchView
        searchItem.expandActionView()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                showResult(query)
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!!.length > 1) {
                    showResult(newText)
                }
                return false
            }
        })
        searchItem.setOnActionExpandListener(this)
        return true
    }

    private fun showResult(query: String?) {
        if (query!!.contains(" "))
            presenter.getTeamSearchResult(query.replaceSpace())
        else
            presenter.getTeamSearchResult(query)

        showTeamSearchResult(team)
    }

    override fun showLoading() {
        pbTeamSearch.visible()
    }

    override fun hideLoading() {
        pbTeamSearch.invisible()
    }

    override fun showTeamSearchResult(data: List<Team>) {
        team.clear()
        data.forEach {
            if (it.sport.equals("Soccer")) {
                team.add(it)
            }
        }
        adapter.notifyDataSetChanged()
        img_empty.invisible()
        tv_empty.invisible()
    }

    override fun showEmpty() {
        team.clear()
        adapter.notifyDataSetChanged()
        img_empty.visible()
        tv_empty.visible()
    }

    override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
        return true
    }

    override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
        onBackPressed()
        return true
    }
}
