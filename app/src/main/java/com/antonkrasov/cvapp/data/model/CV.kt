package com.antonkrasov.cvapp.data.model

import com.google.gson.annotations.SerializedName

data class CV(
    @SerializedName("title") val title: String
)