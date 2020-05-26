package com.dhilasadrah.kadesubmission5.match

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dhilasadrah.kadesubmission5.R
import com.dhilasadrah.kadesubmission5.util.simpleDate
import com.dhilasadrah.kadesubmission5.util.simpleTime

class MatchAdapter(private var match: List<Match>, private var listener: (Match) -> Unit) :
    RecyclerView.Adapter<MatchAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_match_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bindItem(match[position], listener)
    }

    override fun getItemCount(): Int = match.size

    class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val matchDate: TextView = view.findViewById(R.id.match_date)
        private val matchTime: TextView = view.findViewById(R.id.match_time)
        private val homeTeamName: TextView = view.findViewById(R.id.home_team)
        private val homeScore: TextView = view.findViewById(R.id.home_score)
        private val awayTeamName: TextView = view.findViewById(R.id.away_team)
        private val awayScore: TextView = view.findViewById(R.id.away_score)

        fun bindItem(items: Match, listener: (Match) -> Unit) {
            if (items.matchDate.isNullOrEmpty() or items.matchTime.isNullOrEmpty()) {
                matchDate.text = " "
            } else {
                matchDate.text = items.matchDate?.simpleDate()
                matchTime.text = items.matchTime?.simpleTime()
            }
            homeTeamName.text = items.homeTeam
            homeScore.text = items.homeScore
            awayTeamName.text = items.awayTeam
            awayScore.text = items.awayScore

            itemView.setOnClickListener {
                listener(items)
            }
        }
    }
}