package com.dhilasadrah.kadesubmission5

import android.view.KeyEvent
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.dhilasadrah.kadesubmission5.R.id.*
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun tesAppBehaviour() {
        //Memastikan leagueList dan Home BottomNavigationMenu tampil
        Thread.sleep(1000)
        onView(withId(rvLeagueList)).check(matches(isDisplayed()))
        onView(withId(home_nav_view)).check(matches(isDisplayed()))

        //Memberi tindakan klik pada item pertama di leagueList
        onView(withId(rvLeagueList)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        onView(withId(rvLeagueList)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        //Memastikan halaman detail liga dan Details BottomNavigationView tampil
        Thread.sleep(3000)
        onView(withId(navigation_detail)).check(matches(isDisplayed()))
        onView(withId(details_nav_view)).check(matches(isDisplayed()))

        //Memberikan tindakan klik pada menu navigasi "teams" dan memastikan daftar team ditampilkan
        onView(withId(navigation_team)).perform(click())
        Thread.sleep(2000)
        onView(withId(rvTeamList)).check(matches(isDisplayed()))

        //Memberi tindakan klik pada menu search dan memastikan searchView ditampilkan
        onView(withId(search_menu)).perform(click())
        onView(withId(searchView)).check(matches(isDisplayed()))

        //Menuliskan text 'arsenal' pada searchView lalu menekan tombol enter pada keyboard
        onView(withId(searchView)).perform(typeSearchViewText("arsenal"), pressKey(KeyEvent.KEYCODE_ENTER))

        //Memastikan hasil pencarian match ditampilkan, lalu memberikan aksi klik pada item pertama di hasil pencarian team
        Thread.sleep(2000)
        onView(withId(rvTeamSearch)).check(matches(isDisplayed()))
        onView(withId(rvTeamSearch)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        onView(withId(rvTeamSearch)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        //Memastikan halaman detail team yang dicari tampil
        Thread.sleep(2000)
        onView(withId(activity_team_detail)).check(matches(isDisplayed()))

        //Menekan tombol kembali
        Espresso.pressBack()

        //Memberikan aksi klik pada searchView, menuliskan text random 'xyz', lalu menekan tombol enter pada keyboard
        onView(withId(searchView)).perform(click())
        onView(withId(searchView)).perform(typeSearchViewText("xyz"), pressKey(KeyEvent.KEYCODE_ENTER))

        //Memastikan gambar dan text 'No Data Found' ditampilkan saat hasil pencarian tidak ditemukan
        Thread.sleep(1000)
        onView(withId(img_empty)).check(matches(isDisplayed()))
        onView(withId(tv_empty)).check(matches(isDisplayed()))

        //Menekan tombol kembali
        Espresso.pressBack()

        //Memastikan teamList dan bottomNavigationMenu tampil
        Thread.sleep(1000)
        onView(withId(rvTeamList)).check(matches(isDisplayed()))
        onView(withId(details_nav_view)).check(matches(isDisplayed()))

        //Memberikan tindakan klik pada menu navigasi "match" dan memastikan last match list ditampilkan
        onView(withId(navigation_match)).perform(click())
        Thread.sleep(2000)
        onView(withId(rvLastMatch)).check(matches(isDisplayed()))

        //Memastikan menu search ditampilkan, lalu memberi tindakan klik pada menu search
        onView(withId(search_menu)).check(matches(isDisplayed()))
        onView(withId(search_menu)).perform(click())

        //Memastikan searchview ditampilkan, menuliskan text 'arsenal', lalu menekan tombol enter pada keyboard
        onView(withId(searchView)).check(matches(isDisplayed()))
        onView(withId(searchView)).perform(typeSearchViewText("arsenal"), pressKey(KeyEvent.KEYCODE_ENTER))

        //Memastikan hasil pencarian match muncul, lalu memberikan aksi klik pada item kelima di hasil pencarian match
        Thread.sleep(2000)
        onView(withId(rv_matchSearch)).check(matches(isDisplayed()))
        onView(withId(rv_matchSearch)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(4))
        onView(withId(rv_matchSearch)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(4, click()))

        //Memastikan halaman detail match muncul
        Thread.sleep(3000)
        onView(withId(match_detail)).check(matches(isDisplayed()))

        //Menekan tombol kembali
        Espresso.pressBack()

        //Memberikan aksi klik pada searchView, menuliskan text random 'zxc', lalu menekan tombol enter pada keyboard
        onView(withId(searchView)).perform(click())
        onView(withId(searchView)).perform(typeSearchViewText("zxc"), pressKey(KeyEvent.KEYCODE_ENTER))

        //Memastikan gambar dan text 'No Data Found' ditampilkan saat hasil pencarian tidak ditemukan
        Thread.sleep(2000)
        onView(withId(imgEmpty)).check(matches(isDisplayed()))
        onView(withId(tvEmpty)).check(matches(isDisplayed()))
    }
}

fun typeSearchViewText(text: String?): ViewAction? {
    return object : ViewAction {
        override fun getConstraints(): Matcher<View> {
            return allOf(
                isDisplayed(),
                isAssignableFrom(SearchView::class.java)
            )
        }

        override fun getDescription(): String {
            return "Change view text"
        }

        override fun perform(uiController: UiController, view: View) {
            (view as SearchView).setQuery(text, false)
        }
    }
}

