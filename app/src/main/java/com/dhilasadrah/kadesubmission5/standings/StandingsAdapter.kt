package com.dhilasadrah.kadesubmission5.standings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dhilasadrah.kadesubmission5.R

class StandingsAdapter (private var standings: List<Standings>) :
    RecyclerView.Adapter<StandingsAdapter.RecyclerViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_standings, parent, false)
        )
    }

    override fun getItemCount(): Int = standings.size

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bindItem(standings[position])
    }

    class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val teamName: TextView = view.findViewById(R.id.standings_teamName)
        private val played: TextView = view.findViewById(R.id.standings_played)
        private val win: TextView = view.findViewById(R.id.standings_win)
        private val draw: TextView = view.findViewById(R.id.standings_draw)
        private val lose: TextView = view.findViewById(R.id.standings_lose)
        private val total: TextView = view.findViewById(R.id.standings_total)

        fun bindItem(items: Standings) {
            teamName.text = items.teamName
            played.text = items.played
            win.text = items.win
            draw.text = items.draw
            lose.text = items.loss
            total.text = items.total
        }
    }

}