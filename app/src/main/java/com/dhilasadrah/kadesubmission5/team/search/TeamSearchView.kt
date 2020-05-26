package com.dhilasadrah.kadesubmission5.team.search

import com.dhilasadrah.kadesubmission5.team.Team

interface TeamSearchView {
    fun showLoading()
    fun hideLoading()
    fun showTeamSearchResult(data: List<Team>)
    fun showEmpty()
}