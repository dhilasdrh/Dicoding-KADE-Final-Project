package com.dhilasadrah.kadesubmission5.standings

interface StandingsView {
    fun showLoading()
    fun hideLoading()
    fun showStandingsList(data: List<Standings>)
}