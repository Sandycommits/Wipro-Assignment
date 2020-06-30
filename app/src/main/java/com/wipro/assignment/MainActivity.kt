package com.wipro.assignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.wipro.model.InformationData
import com.wipro.model.ResponseData
import com.wipro.utils.*
import com.wipro.viewmodel.InfoViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private var adapter: RecyclerAdapter? = null
    private lateinit var infoViewModel: InfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        swipeRefresh.setOnRefreshListener(this)
        adapter = RecyclerAdapter(ArrayList())
        infoViewModel = InfoViewModel(application)
        fetchApiData()
    }

    override fun onRefresh() {
        adapter?.clear()
        fetchApiData()
    }

    private fun fetchApiData() {
        if (isConnected) {
            infoViewModel.fetchInfoData().observe(this, Observer { onFetchInformation(it) })
        } else {
            swipeRefresh.snack(getString(R.string.no_network))
            swipeRefresh.isRefreshing = false
        }
    }

    private fun onFetchInformation(state: ProcessingState) {
        if (state.state == State.SUCCESS) {
            val responseData = state.extras as ResponseData
            supportActionBar?.title = responseData.title
            loadDate(responseData.informationList)
        } else {
            swipeRefresh.snack(getString(R.string.something_went_wrong))
        }
        swipeRefresh.isRefreshing = false
    }

    private fun loadDate(informationList: ArrayList<InformationData>) {
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        adapter = RecyclerAdapter(informationList)
        recyclerView.adapter = adapter
    }
}
