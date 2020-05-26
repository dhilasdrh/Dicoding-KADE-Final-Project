package com.dhilasadrah.kadesubmission5.presenter

import com.dhilasadrah.kadesubmission5.api.ApiRepository
import com.dhilasadrah.kadesubmission5.api.TheSportDBApi
import com.dhilasadrah.kadesubmission5.coroutine.TestContextProvider
import com.dhilasadrah.kadesubmission5.match.detail.MatchDetail
import com.dhilasadrah.kadesubmission5.match.detail.MatchDetailPresenter
import com.dhilasadrah.kadesubmission5.match.detail.MatchDetailResponse
import com.dhilasadrah.kadesubmission5.match.detail.MatchDetailView
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MatchDetailPresenterTest {
    @Mock private lateinit var view: MatchDetailView
    @Mock private lateinit var gson: Gson
    @Mock private lateinit var apiRepository: ApiRepository
    @Mock private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: MatchDetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MatchDetailPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetMatchDetail() {
        val events: List<MatchDetail> = listOf(MatchDetail())
        val response = MatchDetailResponse(events)
        val idMatch = "441613"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString())).thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    apiRepository.doRequestAsync(TheSportDBApi.getMatchDetail(idMatch)).await(),
                    MatchDetailResponse::class.java
                )
            ).thenReturn(response)

            presenter.getMatchDetail(idMatch)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showMatchDetail(response.events[0])
            Mockito.verify(view).hideLoading()
        }
    }
}