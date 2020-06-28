package com.wipro.model

import com.google.gson.annotations.SerializedName

data class ResponseData(
    @SerializedName("title") val title: String,
    @SerializedName("rows") val informationList: List<InformationData>
)
