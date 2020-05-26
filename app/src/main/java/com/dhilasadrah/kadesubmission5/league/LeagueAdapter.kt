package com.dhilasadrah.kadesubmission5.league

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
import com.dhilasadrah.kadesubmission5.R.*
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class LeagueAdapter(private var leagues: List<League>, private var listener: (League) -> Unit)
    : RecyclerView.Adapter<LeagueAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(LeagueListUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bindItem(leagues[position], listener)
    }

    override fun getItemCount(): Int = leagues.size

    class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val leagueImg: ImageView = view.findViewById(id.id_img)
        private val leagueName: TextView = view.findViewById(id.id_name)

        fun bindItem(items: League, listener: (League) -> Unit) {
            items.image?.let { Picasso.get().load(it).fit().into(leagueImg) }
            leagueName.text = items.name
            itemView.setOnClickListener {
                listener(items)
            }
        }
    }

    class LeagueListUI : AnkoComponent<ViewGroup> {
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
                    id = R.id.id_img
                    padding = dip(8)
                }.lparams(width = dip(130), height = dip(130)){
                    gravity = Gravity.CENTER
                }

                textView {
                    id = R.id.id_name
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
