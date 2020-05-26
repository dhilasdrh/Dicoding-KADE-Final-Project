package com.dhilasadrah.kadesubmission5.league.detail

import com.dhilasadrah.kadesubmission5.coroutine.CoroutineContextProvider
import com.dhilasadrah.kadesubmission5.api.ApiRepository
import com.dhilasadrah.kadesubmission5.api.TheSportDBApi
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LeagueDetailPresenter (
    private val view: LeagueDetailView, private val apiRepository: ApiRepository, private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
){
    fun getLeagueDetail(idLeague: String){
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data =
                gson.fromJson(apiRepository.doRequestAsync(TheSportDBApi.getLeagueDetail(idLeague)).await(),
                    LeagueDetailResponse::class.java
                )

            view.showLeagueDetail(data.leagues[0])
            view.hideLoading()
        }
    }
}