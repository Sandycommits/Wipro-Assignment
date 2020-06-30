package com.wipro.assignment

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.*

class CustomMatchers {
    companion object {
        fun withItemCount(count: Int): BaseMatcher<View> {
            return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {

                override fun matchesSafely(item: RecyclerView?): Boolean {
                    return item?.adapter?.itemCount == count
                }

                override fun describeTo(description: org.hamcrest.Description?) {
                    description?.appendText("RecyclerView with item count: $count")
                }
            }
        }

        fun withIndex(matcher: Matcher<View>, index: Int): Matcher<View> {
            return object : TypeSafeMatcher<View>() {
                var currentIndex: Int = 0
                override fun describeTo(description: Description) {
                    description.appendText("with index: ")
                    description.appendValue(index)
                    matcher.describeTo(description)
                }

                override fun matchesSafely(item: View?): Boolean {
                    return matcher.matches(item) && currentIndex++ == index
                }

            }
        }

        fun hasItemCount(count: Int): ViewAssertion {
            return RecyclerViewItemCountAssertion(count)
        }
    }

    private class RecyclerViewItemCountAssertion(private val count: Int) : ViewAssertion {

        override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
            if (noViewFoundException != null) {
                throw noViewFoundException
            }

            if (view !is RecyclerView) {
                throw IllegalStateException("The asserted view is not RecyclerView")
            }

            if (view.adapter == null) {
                throw IllegalStateException("No adapter is assigned to RecyclerView")
            }

            ViewMatchers.assertThat(
                "RecyclerView item count",
                view.adapter!!.itemCount,
                CoreMatchers.equalTo(count)
            )
        }
    }
}