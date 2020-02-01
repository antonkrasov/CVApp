package com.antonkrasov.cvapp.data

import com.antonkrasov.cvapp.data.model.CV
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface CVApiService {

    @GET("{path}?dl=1")
    fun getCV(
        @Path(
            value = "path",
            encoded = true
        ) path: String
    ): Single<CV>

}