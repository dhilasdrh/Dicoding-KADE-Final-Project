package com.dhilasadrah.kadesubmission5.team

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dhilasadrah.kadesubmission5.R
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class TeamListAdapter(private var team: List<Team>, private var listener: (Team) -> Unit)
    : RecyclerView.Adapter<TeamListAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(TeamListUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bindItem(team[position], listener)
    }

    override fun getItemCount(): Int = team.size

    class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val teamBadge: ImageView = view.findViewById(R.id.teamBadge)
        private val teamName: TextView = view.findViewById(R.id.teamName)

        fun bindItem(items: Team, listener: (Team) -> Unit) {
            items.teamBadge.let { Picasso.get().load(it).fit().into(teamBadge) }
            teamName.text = items.teamName
            itemView.setOnClickListener {
                listener(items)
            }
        }
    }

    class TeamListUI : AnkoComponent<ViewGroup> {
        @SuppressLint("NewApi")
        override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
            verticalLayout {
                lparams(matchParent, wrapContent){
                    margin = dip(4)
                    padding = dip(8)
                }
                background = GradientDrawable().apply {
                    shape = GradientDrawable.RECTANGLE
                    cornerRadius = 10f
                    setColor(Color.WHITE)
                    setStroke(3, Color.LTGRAY)
                }

                imageView{
                    id = R.id.teamBadge
                    padding = dip(8)
                }.lparams(width = dip(130), height = dip(130)){
                    gravity = Gravity.CENTER
                }

                textView {
                    id = R.id.teamName
                    textColor = Color.DKGRAY
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    textSize = 13f
                }.lparams(wrapContent, wrapContent){
                    gravity = Gravity.CENTER
                }
            }
        }
    }
}