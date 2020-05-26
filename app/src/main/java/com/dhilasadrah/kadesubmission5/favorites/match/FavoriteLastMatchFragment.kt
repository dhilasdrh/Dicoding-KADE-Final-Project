package com.dhilasadrah.kadesubmission5.favorites.match

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dhilasadrah.kadesubmission5.R
import com.dhilasadrah.kadesubmission5.match.detail.MatchDetailActivity
import com.dhilasadrah.kadesubmission5.match.detail.MatchDetailActivity.Companion.ID_AWAY_TEAM
import com.dhilasadrah.kadesubmission5.match.detail.MatchDetailActivity.Companion.ID_HOME_TEAM
import com.dhilasadrah.kadesubmission5.match.detail.MatchDetailActivity.Companion.ID_MATCH
import com.dhilasadrah.kadesubmission5.match.detail.MatchDetailActivity.Companion.MATCH_STATUS
import com.dhilasadrah.kadesubmission5.database.database
import com.dhilasadrah.kadesubmission5.util.*
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.startActivity

class FavoritePreviousMatchFragment : Fragment(), AnkoComponent<Context> {
    private var favorites: MutableList<FavoriteMatch> = mutableListOf()
    private var isLastMatch: Boolean = true
    private lateinit var adapter: FavoriteMatchAdapter
    private lateinit var rvFavLastMatch: RecyclerView
    private lateinit var imgEmpty: ImageView
    private lateinit var tvEmpty: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val actionBar: ActionBar? = (activity as AppCompatActivity?)!!.supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        return createView(AnkoContext.create(requireContext()))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = FavoriteMatchAdapter(favorites) {
            startActivity<MatchDetailActivity>(
                ID_MATCH to it.idMatch,
                ID_HOME_TEAM to it.idHomeTeam,
                ID_AWAY_TEAM to it.idAwayTeam,
                MATCH_STATUS to isLastMatch
            )
        }
        rvFavLastMatch.layoutManager = LinearLayoutManager(context)
        rvFavLastMatch.adapter = adapter

        showFavorite()
    }

    private fun showFavorite() {
        favorites.clear()
        context?.database?.use {
            val result = select(FavoriteMatch.TABLE_FAVORITE).whereArgs("(STATUS = {status})",
                "status" to isLastMatch)
            val favorite = result.parseList(classParser<FavoriteMatch>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }

        if (favorites.isNullOrEmpty()) {
            imgEmpty.visible()
            tvEmpty.visible()
        } else {
            imgEmpty.invisible()
            tvEmpty.invisible()
        }
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        relativeLayout {
            lparams(matchParent, matchParent)

            rvFavLastMatch = recyclerView {
                lparams(matchParent, wrapContent)
            }

            imgEmpty = imageView {
                id = R.id.noData
                imageResource = R.drawable.no_data
                visibility = View.VISIBLE
            }.lparams(dip(150), dip(150)) {
                centerInParent()
            }

            tvEmpty = textView {
                text = getString(R.string.no_favorites_yet)
                visibility = View.VISIBLE
            }.lparams(wrapContent, wrapContent) {
                centerInParent()
                below(R.id.noData)
            }
        }
    }
}
