package com.dhilasadrah.kadesubmission5.team.search

import com.dhilasadrah.kadesubmission5.api.ApiRepository
import com.dhilasadrah.kadesubmission5.api.TheSportDBApi
import com.dhilasadrah.kadesubmission5.coroutine.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamSearchPresenter (private val view: TeamSearchView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()
){
    fun getTeamSearchResult(query: String?) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequestAsync(TheSportDBApi.getTeamSearch(query)).await(),
                TeamSearchResponse::class.java
            )

            if (data.teams.isNullOrEmpty())
                view.showEmpty()
            else
                view.showTeamSearchResult(data.teams)

            view.hideLoading()
        }
    }
}