package com.dhilasadrah.kadesubmission5.league.detail

interface LeagueDetailView {
    fun showLoading()
    fun hideLoading()
    fun showLeagueDetail(data: LeagueDetail)
}