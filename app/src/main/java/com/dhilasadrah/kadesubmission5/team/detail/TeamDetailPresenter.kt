package com.dhilasadrah.kadesubmission5.team.detail

import com.dhilasadrah.kadesubmission5.api.ApiRepository
import com.dhilasadrah.kadesubmission5.api.TheSportDBApi
import com.dhilasadrah.kadesubmission5.coroutine.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamDetailPresenter(private val view: TeamDetailView, private val apiRepository: ApiRepository,
                          private val gson: Gson,
                          private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getTeamDetail(idTeam: String?) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequestAsync(TheSportDBApi.getTeamDetail(idTeam)).await(),
                TeamDetailResponse::class.java
            )

            view.showTeamDetail(data.teams[0])
            view.hideLoading()
        }
    }
}