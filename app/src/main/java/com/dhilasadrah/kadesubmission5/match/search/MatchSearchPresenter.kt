package com.dhilasadrah.kadesubmission5.match.search

import com.dhilasadrah.kadesubmission5.coroutine.CoroutineContextProvider
import com.dhilasadrah.kadesubmission5.api.ApiRepository
import com.dhilasadrah.kadesubmission5.api.TheSportDBApi
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MatchSearchPresenter(private val view: MatchSearchView, private val apiRepository: ApiRepository,
                           private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getMatchSearchResult(query: String?) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequestAsync(TheSportDBApi.getMatchSearch(query)).await(),
                MatchSearchResponse::class.java
            )

            if (data.event.isNullOrEmpty())
                view.showEmpty()
            else
                view.showMatchSearchResult(data.event)

            view.hideLoading()
        }
    }
}