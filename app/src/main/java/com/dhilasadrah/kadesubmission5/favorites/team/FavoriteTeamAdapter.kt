package com.dhilasadrah.kadesubmission5.favorites.team

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dhilasadrah.kadesubmission5.R
import com.dhilasadrah.kadesubmission5.team.TeamListAdapter
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext

class FavoriteTeamAdapter(private val favoriteTeam: List<FavoriteTeam>, private val listener: (FavoriteTeam) -> Unit
) : RecyclerView.Adapter<FavoriteTeamAdapter.FavTeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavTeamViewHolder {
        return FavTeamViewHolder(TeamListAdapter.TeamListUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: FavTeamViewHolder, position: Int) {
        holder.bindItem(favoriteTeam[position], listener)
    }

    override fun getItemCount(): Int = favoriteTeam.size

    class FavTeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val teamName: TextView = view.findViewById(R.id.teamName)
        private val teamBadge: ImageView = view.findViewById(R.id.teamBadge)

        fun bindItem(favoriteTeam: FavoriteTeam, listener: (FavoriteTeam) -> Unit) {
            teamName.text = favoriteTeam.teamName
            Picasso.get().load(favoriteTeam.teamBadge).into(teamBadge)

            itemView.setOnClickListener {
                listener(favoriteTeam)
            }
        }
    }
}
