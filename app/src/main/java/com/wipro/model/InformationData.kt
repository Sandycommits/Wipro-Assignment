package com.wipro.model

import com.google.gson.annotations.SerializedName

class InformationData(
    @SerializedName("title") val title: String? = "Title",
    @SerializedName("description") val description: String,
    @SerializedName("imageHref") val image: String
)