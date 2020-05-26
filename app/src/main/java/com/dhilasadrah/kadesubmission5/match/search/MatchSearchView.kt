package com.dhilasadrah.kadesubmission5.match.search

import com.dhilasadrah.kadesubmission5.match.Match

interface MatchSearchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchSearchResult(data: List<Match>)
    fun showEmpty()
}