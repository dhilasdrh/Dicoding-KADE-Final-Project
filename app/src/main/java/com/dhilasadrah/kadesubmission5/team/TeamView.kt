package com.dhilasadrah.kadesubmission5.team

interface TeamView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}