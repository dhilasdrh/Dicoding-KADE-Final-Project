package com.dhilasadrah.kadesubmission5.presenter

import com.dhilasadrah.kadesubmission5.api.ApiRepository
import com.dhilasadrah.kadesubmission5.api.TheSportDBApi
import com.dhilasadrah.kadesubmission5.coroutine.TestContextProvider
import com.dhilasadrah.kadesubmission5.match.Match
import com.dhilasadrah.kadesubmission5.match.MatchPresenter
import com.dhilasadrah.kadesubmission5.match.MatchResponse
import com.dhilasadrah.kadesubmission5.match.MatchView
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
class MatchPresenterTest {
    @Mock private lateinit var view: MatchView
    @Mock private lateinit var gson: Gson
    @Mock private lateinit var apiRepository: ApiRepository
    @Mock private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: MatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetPreviousMatchList() {
        val events: MutableList<Match> = mutableListOf()
        val response = MatchResponse(events)
        val idLeague = "4328"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString())).thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    apiRepository.doRequestAsync(TheSportDBApi.getPastMatch(idLeague)).await(),
                    MatchResponse::class.java
                )
            ).thenReturn(response)

            presenter.getNextMatch(idLeague)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showMatchList(events)
            Mockito.verify(view).hideLoading()
        }
    }

    @Test
    fun testGetNextMatchList() {
        val events: MutableList<Match> = mutableListOf()
        val response =
            MatchResponse(events)
        val idLeague = "4328"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString())).thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    apiRepository.doRequestAsync(TheSportDBApi.getNextMatch(idLeague)).await(),
                    MatchResponse::class.java
                )
            ).thenReturn(response)

            presenter.getNextMatch(idLeague)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showMatchList(events)
            Mockito.verify(view).hideLoading()
        }
    }
}