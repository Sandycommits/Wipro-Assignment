package com.wipro.assignment

import android.content.res.Resources
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.rule.ActivityTestRule
import com.wipro.assignment.CustomMatchers.Companion.hasItemCount
import com.wipro.assignment.CustomMatchers.Companion.withIndex
import com.wipro.assignment.CustomMatchers.Companion.withItemCount
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)


    @Test
    fun testRecyclerViewItemsCount() {
        Thread.sleep(5000)
        onView(withId(R.id.recyclerView))
            .check(matches(withItemCount(14)))
    }

    @Test
    fun testRecyclerWithViewAssertion() {
        Thread.sleep(5000)
        onView(withId(R.id.recyclerView))
            .check(hasItemCount(14))
    }

    @Test
    fun testRecyclerViewItem() {
        Thread.sleep(5000)
        onView(withId(R.id.recyclerView)).check(matches(hasDescendant(withId(R.id.titleText))))
        onView(withId(R.id.recyclerView)).check(matches(hasDescendant(withId(R.id.descriptionText))))
        onView(withId(R.id.recyclerView)).check(matches(hasDescendant(withId(R.id.imageView))))

        val title: Matcher<View> = allOf(withId(R.id.titleText))
        onView(withIndex(title, 0)).check(matches(isDisplayed()))
        onView(withIndex(title, 0)).check(matches(not(withText(""))))

        val description: Matcher<View> = allOf(withId(R.id.descriptionText))
        onView(withIndex(description, 0)).check(matches(isDisplayed()))
        onView(withIndex(description, 0)).check(matches(not(withText(""))))

        val imageView: Matcher<View> = allOf(withId(R.id.imageView))
        onView(withIndex(imageView, 0)).check(matches(isDisplayed()))
    }

    @Test
    fun testRecyclerViewNoData() {
        onView(withId(R.id.recyclerView))
            .check(matches(withItemCount(0)))
    }

    @Test
    fun testActionBarTitle() {
        Thread.sleep(5000)
        val resources: Resources = getInstrumentation().getTargetContext().getResources()
        val actionBarId: Int = resources.getIdentifier(
            "action_bar_container",
            "id",
            activityRule.activity.packageName
        )
        onView(withId(actionBarId)).check(matches(isDisplayed()))
        onView(withId(actionBarId)).check(matches(not(withText(""))))
    }
}