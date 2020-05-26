package com.dhilasadrah.kadesubmission5.standings

import com.dhilasadrah.kadesubmission5.api.ApiRepository
import com.dhilasadrah.kadesubmission5.api.TheSportDBApi
import com.dhilasadrah.kadesubmission5.coroutine.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StandingsPresenter(private val view: StandingsView, private val apiRepository: ApiRepository,
                         private val gson: Gson,
                         private val context: CoroutineContextProvider = CoroutineContextProvider()
){
    fun getStandingsList(idLeague: String?) {
        view.showLoading()
        GlobalScope.launch (context.main) {
            val data =  gson.fromJson(
                apiRepository.doRequestAsync(TheSportDBApi.getStandings(idLeague)).await(),
                StandingsResponse::class.java
            )

            view.showStandingsList(data.table)
            view.hideLoading()
        }
    }
}