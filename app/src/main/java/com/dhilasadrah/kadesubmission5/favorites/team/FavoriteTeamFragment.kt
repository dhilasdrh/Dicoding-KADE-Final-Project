package com.dhilasadrah.kadesubmission5.favorites.team

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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.dhilasadrah.kadesubmission5.R
import com.dhilasadrah.kadesubmission5.team.detail.TeamDetailActivity
import com.dhilasadrah.kadesubmission5.team.detail.TeamDetailActivity.Companion.TEAM_ID
import com.dhilasadrah.kadesubmission5.team.detail.TeamDetailActivity.Companion.TEAM_NAME
import com.dhilasadrah.kadesubmission5.database.database
import com.dhilasadrah.kadesubmission5.util.*
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.startActivity

class FavoriteTeamFragment : Fragment(), AnkoComponent<Context> {
    private var favoriteTeam: MutableList<FavoriteTeam> = mutableListOf()
    private lateinit var adapter: FavoriteTeamAdapter
    private lateinit var rvFavTeam: RecyclerView
    private lateinit var imgEmpty: ImageView
    private lateinit var tvEmpty: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val actionBar: ActionBar? = (activity as AppCompatActivity?)!!.supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        return createView(AnkoContext.create(requireContext()))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = FavoriteTeamAdapter(favoriteTeam) {
            startActivity<TeamDetailActivity>(
                TEAM_ID to it.idTeam,
                TEAM_NAME to it.teamName
            )
        }
        rvFavTeam.layoutManager = GridLayoutManager(context, 3)
        rvFavTeam.adapter = adapter

        showFavorite()
    }

    private fun showFavorite() {
        favoriteTeam.clear()
        context?.database?.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            favoriteTeam.addAll(favorite)
            adapter.notifyDataSetChanged()
        }

        if (favoriteTeam.isNullOrEmpty()) {
            imgEmpty.visible()
            imgEmpty.visible()
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

            rvFavTeam = recyclerView {
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
