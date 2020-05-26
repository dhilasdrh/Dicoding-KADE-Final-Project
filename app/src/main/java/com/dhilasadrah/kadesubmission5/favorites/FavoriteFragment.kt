package com.dhilasadrah.kadesubmission5.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.dhilasadrah.kadesubmission5.R
import com.google.android.material.tabs.TabLayout

class FavoriteFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_favorite, container, false)

        val viewPager: ViewPager = root.findViewById(R.id.fav_viewPager)
        val tabLayout: TabLayout = root.findViewById(R.id.fav_tabLayout)

        val favMatchPagerAdapter = FavoritesPagerAdapter(context, childFragmentManager)
        viewPager.adapter = favMatchPagerAdapter
        tabLayout.setupWithViewPager(viewPager)

        val actionBar: ActionBar? = (activity as AppCompatActivity?)!!.supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        return root
    }
}