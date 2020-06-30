package com.wipro.utils

class ProcessingState(var state: State, var error: Throwable?, var extras: Any?) {

    /**
     * This static properties used to return the response state after the API call.
     */
    companion object {
        val errorState = ProcessingState(State.FAILED, Throwable(), null)
        val successState = ProcessingState(State.SUCCESS, Throwable(), Any())
    }
}