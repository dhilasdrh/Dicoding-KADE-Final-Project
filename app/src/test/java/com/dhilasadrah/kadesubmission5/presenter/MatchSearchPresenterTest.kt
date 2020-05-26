package com.dhilasadrah.kadesubmission5.presenter

import com.dhilasadrah.kadesubmission5.api.ApiRepository
import com.dhilasadrah.kadesubmission5.api.TheSportDBApi
import com.dhilasadrah.kadesubmission5.coroutine.TestContextProvider
import com.dhilasadrah.kadesubmission5.match.Match
import com.dhilasadrah.kadesubmission5.match.search.MatchSearchPresenter
import com.dhilasadrah.kadesubmission5.match.search.MatchSearchResponse
import com.dhilasadrah.kadesubmission5.match.search.MatchSearchView
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MatchSearchPresenterTest {
    @Mock private lateinit var view: MatchSearchView
    @Mock private lateinit var gson: Gson
    @Mock private lateinit var apiRepository: ApiRepository
    @Mock private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: MatchSearchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MatchSearchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetMatchSearch() {
        val event: MutableList<Match> = mutableListOf()
        val response = MatchSearchResponse(event)
        val query = "arsenal"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString())).thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    apiRepository.doRequestAsync(TheSportDBApi.getMatchSearch(query)).await(),
                    MatchSearchResponse::class.java
                )
            ).thenReturn(response)

            presenter.getMatchSearchResult(query)

            Mockito.verify(view).showLoading()
            if (response.event.isNullOrEmpty())
                Mockito.verify(view).showEmpty()
            else
                Mockito.verify(view).showMatchSearchResult(response.event)
            Mockito.verify(view).hideLoading()
        }
    }
}

