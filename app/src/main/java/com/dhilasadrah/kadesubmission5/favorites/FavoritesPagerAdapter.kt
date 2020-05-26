package com.dhilasadrah.kadesubmission5.favorites

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dhilasadrah.kadesubmission5.R
import com.dhilasadrah.kadesubmission5.favorites.match.FavoriteNextMatchFragment
import com.dhilasadrah.kadesubmission5.favorites.match.FavoritePreviousMatchFragment
import com.dhilasadrah.kadesubmission5.favorites.team.FavoriteTeamFragment

class FavoritesPagerAdapter(private val mContext: Context?, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    @StringRes
    private val tabTitle = intArrayOf(R.string.last_match, R.string.next_match, R.string.title_team)

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FavoritePreviousMatchFragment()
            1 -> fragment = FavoriteNextMatchFragment()
            2 -> fragment = FavoriteTeamFragment()
        }
        return fragment as Fragment
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mContext?.resources?.getString(tabTitle[position])
    }
}