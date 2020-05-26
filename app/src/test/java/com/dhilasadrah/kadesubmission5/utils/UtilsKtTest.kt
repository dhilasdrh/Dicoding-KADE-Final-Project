package com.dhilasadrah.kadesubmission5.utils

import com.dhilasadrah.kadesubmission5.util.*
import org.junit.Assert.assertEquals
import org.junit.Test

class UtilsKtTest {
    @Test
    fun testSimpleDate() {
        val date = "29/01/2020"
        val expectedDate = "Wed, 29 Jan 2020"
        assertEquals(expectedDate, date.simpleDate())
    }

    @Test
    fun testSimpleTime() {
        val time = "14:00:00"
        val expectedTime = "02:00 PM"
        assertEquals(expectedTime, time.simpleTime())
    }

    @Test
    fun testParseLineup() {
        val lineup = "Mamadou Sakho; Emre Can; Martin Skrtel;"
        val expectedLineup = "Mamadou Sakho;\nEmre Can;\nMartin Skrtel;"
        assertEquals(expectedLineup, lineup.parseLineup())
    }

    @Test
    fun testParseDetail() {
        val detail = "78': Moussa Sissoko;26': Jack Colback;"
        val expectedDetail = "78': Moussa Sissoko;\n26': Jack Colback;\n"
        assertEquals(expectedDetail, detail.parseDetail())
    }

    @Test
    fun testReplaceSpace() {
        val text = "Man United"
        val expectedText = "Man%20United"
        assertEquals(expectedText, text.replaceSpace())
    }
}