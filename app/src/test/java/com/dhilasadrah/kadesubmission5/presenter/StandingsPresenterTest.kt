package com.dhilasadrah.kadesubmission5.presenter

import com.dhilasadrah.kadesubmission5.api.ApiRepository
import com.dhilasadrah.kadesubmission5.api.TheSportDBApi
import com.dhilasadrah.kadesubmission5.coroutine.TestContextProvider
import com.dhilasadrah.kadesubmission5.standings.Standings
import com.dhilasadrah.kadesubmission5.standings.StandingsPresenter
import com.dhilasadrah.kadesubmission5.standings.StandingsResponse
import com.dhilasadrah.kadesubmission5.standings.StandingsView
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
class StandingsPresenterTest {
    @Mock private lateinit var view: StandingsView
    @Mock private lateinit var gson: Gson
    @Mock private lateinit var apiRepository: ApiRepository
    @Mock private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: StandingsPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = StandingsPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testStandingsList() {
        val tables: MutableList<Standings> = mutableListOf()
        val response = StandingsResponse(tables)
        val idLeague = "4328"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString())).thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    apiRepository.doRequestAsync(TheSportDBApi.getStandings(idLeague)).await(),
                    StandingsResponse::class.java
                )
            ).thenReturn(response)

            presenter.getStandingsList(idLeague)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showStandingsList(tables)
            Mockito.verify(view).hideLoading()
        }
    }
}