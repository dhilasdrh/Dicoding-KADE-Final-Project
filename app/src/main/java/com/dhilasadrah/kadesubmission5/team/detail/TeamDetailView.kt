package com.dhilasadrah.kadesubmission5.team.detail

interface TeamDetailView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: TeamDetail)
}