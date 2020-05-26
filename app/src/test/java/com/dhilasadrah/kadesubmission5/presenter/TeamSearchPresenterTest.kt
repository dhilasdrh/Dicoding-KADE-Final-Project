package com.dhilasadrah.kadesubmission5.presenter

import com.dhilasadrah.kadesubmission5.api.ApiRepository
import com.dhilasadrah.kadesubmission5.api.TheSportDBApi
import com.dhilasadrah.kadesubmission5.coroutine.TestContextProvider
import com.dhilasadrah.kadesubmission5.team.Team
import com.dhilasadrah.kadesubmission5.team.search.TeamSearchResponse
import com.dhilasadrah.kadesubmission5.team.search.TeamSearchPresenter
import com.dhilasadrah.kadesubmission5.team.search.TeamSearchView
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
class TeamSearchPresenterTest {
    @Mock private lateinit var view: TeamSearchView
    @Mock private lateinit var gson: Gson
    @Mock private lateinit var apiRepository: ApiRepository
    @Mock private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: TeamSearchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = TeamSearchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetTeamSearch() {
        val teams: MutableList<Team> = mutableListOf()
        val response = TeamSearchResponse(teams)
        val query = "arsenal"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString())).thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    apiRepository.doRequestAsync(TheSportDBApi.getTeamSearch(query)).await(),
                    TeamSearchResponse::class.java)
            ).thenReturn(response)

            presenter.getTeamSearchResult(query)

            Mockito.verify(view).showLoading()
            if (response.teams.isNullOrEmpty())
                Mockito.verify(view).showEmpty()
            else
                Mockito.verify(view).showTeamSearchResult(response.teams)
            Mockito.verify(view).hideLoading()
        }
    }
}