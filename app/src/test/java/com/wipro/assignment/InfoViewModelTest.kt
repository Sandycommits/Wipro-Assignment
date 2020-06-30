package com.wipro.assignment

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.wipro.model.InformationData
import com.wipro.model.ResponseData
import com.wipro.network.ApiClient
import com.wipro.viewmodel.InfoViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Call

class InfoViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var apiClient: ApiClient

    private lateinit var application: Application

    lateinit var infoViewModel: InfoViewModel

    private lateinit var responseData: ResponseData

    private lateinit var response: Call<ResponseData>

    @Before
    fun setUp() {
        application = Application()
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun onFetchResponseSuccess() {
        infoViewModel = InfoViewModel(application)
        val infoData = InformationData(
            "Public Shame",
            "Sadly it's true",
            "http://static.guim.co.uk/sys-images/Music/Pix/site_furniture/2007/04/19/avril_lavigne.jpg"
        )
        val infoDataList: ArrayList<InformationData> = ArrayList()
        infoDataList.add(infoData)
        responseData = ResponseData("About Canada", infoDataList)
        infoViewModel.fetchInfoData()
        Mockito.`when`(apiClient.getClient().fetchData()).thenReturn(response)
        Assert.assertEquals("About Canada", responseData.title)
        Assert.assertEquals(1, responseData.informationList.size)
        Assert.assertEquals("Public Shame", responseData.informationList.get(0).title)
        Assert.assertEquals("Sadly it's true", responseData.informationList.get(0).description)
        Assert.assertNotNull(responseData.informationList.get(0).image)
    }
}
