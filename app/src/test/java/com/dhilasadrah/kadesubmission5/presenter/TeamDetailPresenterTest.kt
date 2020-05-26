package com.dhilasadrah.kadesubmission5.presenter

import com.dhilasadrah.kadesubmission5.api.ApiRepository
import com.dhilasadrah.kadesubmission5.api.TheSportDBApi
import com.dhilasadrah.kadesubmission5.coroutine.TestContextProvider
import com.dhilasadrah.kadesubmission5.team.detail.TeamDetail
import com.dhilasadrah.kadesubmission5.team.detail.TeamDetailResponse
import com.dhilasadrah.kadesubmission5.team.detail.TeamDetailPresenter
import com.dhilasadrah.kadesubmission5.team.detail.TeamDetailView
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
class TeamDetailPresenterTest {
    @Mock private lateinit var view: TeamDetailView
    @Mock private lateinit var gson: Gson
    @Mock private lateinit var apiRepository: ApiRepository
    @Mock private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: TeamDetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = TeamDetailPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testTeamDetail() {
        val teams: List<TeamDetail> = listOf(TeamDetail())
        val response = TeamDetailResponse(teams)
        val idTeam = "133604"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString())).thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    apiRepository.doRequestAsync(TheSportDBApi.getTeamDetail(idTeam)).await(),
                    TeamDetailResponse::class.java
                )
            ).thenReturn(response)

            presenter.getTeamDetail(idTeam)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showTeamDetail(response.teams[0])
            Mockito.verify(view).hideLoading()
        }
    }
}