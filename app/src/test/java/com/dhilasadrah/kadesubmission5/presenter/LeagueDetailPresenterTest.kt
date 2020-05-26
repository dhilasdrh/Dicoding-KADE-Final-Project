package com.dhilasadrah.kadesubmission5.presenter

import com.dhilasadrah.kadesubmission5.api.ApiRepository
import com.dhilasadrah.kadesubmission5.api.TheSportDBApi
import com.dhilasadrah.kadesubmission5.coroutine.TestContextProvider
import com.dhilasadrah.kadesubmission5.league.detail.LeagueDetail
import com.dhilasadrah.kadesubmission5.league.detail.LeagueDetailPresenter
import com.dhilasadrah.kadesubmission5.league.detail.LeagueDetailResponse
import com.dhilasadrah.kadesubmission5.league.detail.LeagueDetailView
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class LeagueDetailPresenterTest {
    @Mock private lateinit var view: LeagueDetailView
    @Mock private lateinit var gson: Gson
    @Mock private lateinit var apiRepository: ApiRepository
    @Mock private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: LeagueDetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = LeagueDetailPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetLeagueDetail() {
        val leagues: List<LeagueDetail> = listOf(LeagueDetail())
        val response = LeagueDetailResponse(leagues)
        val idLeague = "4328"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString())).thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    apiRepository.doRequestAsync(TheSportDBApi.getLeagueDetail(idLeague)).await(),
                    LeagueDetailResponse::class.java
                )
            ).thenReturn(response)

            presenter.getLeagueDetail(idLeague)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showLeagueDetail(response.leagues[0])
            Mockito.verify(view).hideLoading()
        }
    }
}