package com.dhilasadrah.kadesubmission5.match.search

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.dhilasadrah.kadesubmission5.R
import com.dhilasadrah.kadesubmission5.match.detail.MatchDetailActivity.Companion.ID_AWAY_TEAM
import com.dhilasadrah.kadesubmission5.match.detail.MatchDetailActivity.Companion.ID_HOME_TEAM
import com.dhilasadrah.kadesubmission5.match.detail.MatchDetailActivity.Companion.ID_MATCH
import com.dhilasadrah.kadesubmission5.match.detail.MatchDetailActivity.Companion.MATCH_STATUS
import com.dhilasadrah.kadesubmission5.api.ApiRepository
import com.dhilasadrah.kadesubmission5.match.Match
import com.dhilasadrah.kadesubmission5.match.MatchAdapter
import com.dhilasadrah.kadesubmission5.match.detail.MatchDetailActivity
import com.dhilasadrah.kadesubmission5.util.invisible
import com.dhilasadrah.kadesubmission5.util.replaceSpace
import com.dhilasadrah.kadesubmission5.util.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_match_search.*
import org.jetbrains.anko.startActivity

class MatchSearchActivity : AppCompatActivity(), MatchSearchView, MenuItem.OnActionExpandListener {
    private var match: MutableList<Match> = mutableListOf()
    private var isLastMatch: Boolean = false
    private lateinit var presenter: MatchSearchPresenter
    private lateinit var adapter: MatchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_search)

        presenter = MatchSearchPresenter(this, ApiRepository(),Gson())
        adapter = MatchAdapter(match) {
            isLastMatch = !it.homeScore.isNullOrEmpty()
            startActivity<MatchDetailActivity>(
                ID_MATCH to it.idEvent,
                ID_HOME_TEAM to it.idHomeTeam,
                ID_AWAY_TEAM to it.idAwayTeam,
                MATCH_STATUS to isLastMatch
            )
        }
        rv_matchSearch.layoutManager = LinearLayoutManager(this)
        rv_matchSearch.adapter = adapter
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
            presenter.getMatchSearchResult(query.replaceSpace())
        else
            presenter.getMatchSearchResult(query)

        showMatchSearchResult(match)
    }

    override fun showLoading() {
        pbSearch.visible()
    }

    override fun hideLoading() {
        pbSearch.invisible()
    }

    override fun showMatchSearchResult(data: List<Match>) {
        match.clear()
        data.forEach {
            if (it.sport.equals("Soccer")) {
                match.add(it)
            }
        }
        adapter.notifyDataSetChanged()
        imgEmpty.invisible()
        tvEmpty.invisible()
    }

    override fun showEmpty() {
        match.clear()
        adapter.notifyDataSetChanged()
        imgEmpty.visible()
        tvEmpty.visible()
    }

    override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
        return true
    }

    override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
        onBackPressed()
        return true
    }

}
