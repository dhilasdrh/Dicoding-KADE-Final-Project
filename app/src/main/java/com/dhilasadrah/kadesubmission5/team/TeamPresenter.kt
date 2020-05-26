package com.dhilasadrah.kadesubmission5.team

import com.dhilasadrah.kadesubmission5.api.ApiRepository
import com.dhilasadrah.kadesubmission5.api.TheSportDBApi
import com.dhilasadrah.kadesubmission5.coroutine.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamPresenter(private val view: TeamView, private val apiRepository: ApiRepository, private val gson: Gson,
                    private val context: CoroutineContextProvider = CoroutineContextProvider()
){
    fun getTeamList(idLeague: String?) {
        view.showLoading()
        GlobalScope.launch (context.main) {
            val data =  gson.fromJson(
                apiRepository.doRequestAsync(TheSportDBApi.getAllTeams(idLeague)).await(),
                TeamResponse::class.java
            )

            view.showTeamList(data.teams)
            view.hideLoading()
        }
    }
}