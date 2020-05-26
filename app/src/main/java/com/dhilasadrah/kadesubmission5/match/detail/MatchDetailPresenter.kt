package com.dhilasadrah.kadesubmission5.match.detail

import com.dhilasadrah.kadesubmission5.coroutine.CoroutineContextProvider
import com.dhilasadrah.kadesubmission5.api.ApiRepository
import com.dhilasadrah.kadesubmission5.api.TheSportDBApi
import com.dhilasadrah.kadesubmission5.team.TeamResponse
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MatchDetailPresenter(
    private val view: MatchDetailView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getMatchDetail(idEvent: String?) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequestAsync(TheSportDBApi.getMatchDetail(idEvent)).await(),
                MatchDetailResponse::class.java
            )

            view.showMatchDetail(data.events[0])
            view.hideLoading()
        }
    }

    fun getTeamBadge(idTeam: String?, isHomeTeam: Boolean = true) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequestAsync(TheSportDBApi.getTeamDetail(idTeam)).await(),
                TeamResponse::class.java
            )

            view.showTeamBadge(data.teams[0], isHomeTeam)
            view.hideLoading()
        }
    }
}