package com.wipro.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.wipro.model.ResponseData
import com.wipro.network.ApiClient
import com.wipro.utils.ProcessingState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Response

class InfoViewModel(application: Application) : AndroidViewModel(application) {

    fun fetchInfoData(): MutableLiveData<ProcessingState> {
        val responseLiveData: MutableLiveData<ProcessingState> = MutableLiveData()
        fetchApiResponse().observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread()).doOnSuccess(responseLiveData::postValue)
            .doOnError { responseLiveData.postValue(ProcessingState.errorState) }
            .subscribe()
        return responseLiveData
    }

    private fun fetchApiResponse(): Single<ProcessingState> {
        return Single.create<ProcessingState> {
            val apiInterface = ApiClient.getClient()
            val call: Call<ResponseData> = apiInterface.fetchData()
            call.enqueue(object : retrofit2.Callback<ResponseData> {

                override fun onResponse(
                    call: Call<ResponseData>,
                    response: Response<ResponseData>
                ) {
                    ProcessingState.successState.extras = response.body()
                    it.onSuccess(ProcessingState.successState)
                }

                override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                    ProcessingState.errorState.error = t
                    it.onError(ProcessingState.errorState.error)
                }
            })
        }
    }
}
