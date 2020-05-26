package com.dhilasadrah.kadesubmission5.match.detail

import com.dhilasadrah.kadesubmission5.team.Team

interface MatchDetailView {
    fun showLoading()
    fun hideLoading()
    fun showMatchDetail(data: MatchDetail)
    fun showTeamBadge(team: Team, isHomeTeam: Boolean)
}