package com.dhilasadrah.kadesubmission5.api

import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class ApiRepositoryTest {
    @Test
    fun testDoRequest() {
        val apiRepository = mock(ApiRepository::class.java)
        val url = "https://thesportsdb.com/api/v1/json/1/lookupleague.php?id=4328"
        apiRepository.doRequestAsync(url)
        verify(apiRepository).doRequestAsync(url)
    }
}