package com.dhilasadrah.kadesubmission5.league

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.dhilasadrah.kadesubmission5.DetailsActivity
import com.dhilasadrah.kadesubmission5.R
import com.dhilasadrah.kadesubmission5.league.detail.LeagueDetailFragment.Companion.LEAGUE_ID
import com.dhilasadrah.kadesubmission5.league.detail.LeagueDetailFragment.Companion.LEAGUE_NAME
import kotlinx.android.synthetic.main.fragment_league.*
import org.jetbrains.anko.support.v4.startActivity

class LeagueFragment : Fragment(){
    private var leagues: MutableList<League> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_league, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        rvLeagueList.layoutManager = GridLayoutManager(context, 2)
        rvLeagueList.adapter = LeagueAdapter(leagues) {
                startActivity<DetailsActivity>(LEAGUE_ID to it.id, LEAGUE_NAME to it.name)
            }

        val actionBar: ActionBar? = (activity as AppCompatActivity?)!!.supportActionBar
        actionBar?.title = getString(R.string.app_name)
    }

    private fun initData() {
        val image = resources.obtainTypedArray(R.array.league_img)
        val name = resources.getStringArray(R.array.league_name)
        val id = resources.getStringArray(R.array.league_id)

        leagues.clear()
        for (i in name.indices) {
            leagues.add(
                League(id[i], name[i], image.getResourceId(i, 0))
            )
        }
        image.recycle()
    }
}