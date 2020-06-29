package com.wipro.assignment

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.wipro.model.ResponseData
import com.wipro.utils.ProcessingState
import com.wipro.utils.State
import com.wipro.viewmodel.InfoViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val infoViewModel = InfoViewModel(application)
        infoViewModel.fetchInfoData().observe(this, Observer { onFetchInformation(it) })
    }

    private fun onFetchInformation(state: ProcessingState) {
        if (state.state == State.SUCCESS) {
            val responseData = state.extras as ResponseData
            Log.i("Action bar title:::::", responseData.title)
        }
    }
}
