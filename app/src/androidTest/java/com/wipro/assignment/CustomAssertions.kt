package com.wipro.assignment

class CustomAssertions {
    companion object {
//        fun hasItemCount(count: Int): ViewAssertion {
//            return RecyclerViewItemCountAssertion(count)
//        }
    }

//    class RecyclerViewItemCountAssertion(private val count: Int) : ViewAssertion {
//
//        override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
//            if (noViewFoundException != null) {
//                throw noViewFoundException
//            }
//
//            if (view !is RecyclerView) {
//                throw IllegalStateException("The asserted view is not RecyclerView")
//            }
//
//            if (view.adapter == null) {
//                throw IllegalStateException("No adapter is assigned to RecyclerView")
//            }
//
//            ViewMatchers.assertThat("RecyclerView item count", view.adapter!!.itemCount, CoreMatchers.equalTo(count))
//        }
//    }
}