package com.dhilasadrah.kadesubmission5.favorites.match

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dhilasadrah.kadesubmission5.R
import com.dhilasadrah.kadesubmission5.util.simpleDate
import com.dhilasadrah.kadesubmission5.util.simpleTime

class FavoriteMatchAdapter(private val favoriteMatch: List<FavoriteMatch>, private val listener: (FavoriteMatch) -> Unit
) : RecyclerView.Adapter<FavoriteMatchAdapter.FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_match_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(favoriteMatch[position], listener)
    }

    override fun getItemCount(): Int = favoriteMatch.size

    class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val matchDate: TextView = view.findViewById(R.id.match_date)
        private val matchTime: TextView = view.findViewById(R.id.match_time)
        private val homeTeamName: TextView = view.findViewById(R.id.home_team)
        private val homeTeamScore: TextView = view.findViewById(R.id.home_score)
        private val awayTeamName: TextView = view.findViewById(R.id.away_team)
        private val awayTeamScore: TextView = view.findViewById(R.id.away_score)

        fun bindItem(favoriteMatch: FavoriteMatch, listener: (FavoriteMatch) -> Unit) {
            if (favoriteMatch.matchDate.isNullOrEmpty() or favoriteMatch.matchTime.isNullOrEmpty()) {
                matchDate.text = " "
            } else {
                matchDate.text = favoriteMatch.matchDate?.simpleDate()
                matchTime.text = favoriteMatch.matchTime?.simpleTime()
            }
            homeTeamName.text = favoriteMatch.homeTeamName
            homeTeamScore.text = favoriteMatch.homeTeamScore
            awayTeamName.text = favoriteMatch.awayTeamName
            awayTeamScore.text = favoriteMatch.awayTeamScore

            itemView.setOnClickListener {
                listener(favoriteMatch)
            }
        }
    }
}
