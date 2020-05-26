package com.dhilasadrah.kadesubmission5.match

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<Match>)
}