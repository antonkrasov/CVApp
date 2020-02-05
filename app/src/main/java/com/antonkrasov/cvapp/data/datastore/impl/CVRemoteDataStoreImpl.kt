package com.antonkrasov.cvapp.data.datastore.impl

import com.antonkrasov.cvapp.CV_PATH
import com.antonkrasov.cvapp.data.api.CVApiService
import com.antonkrasov.cvapp.data.datastore.CVRemoteDataStore
import com.antonkrasov.cvapp.data.model.CV
import io.reactivex.Flowable

class CVRemoteDataStoreImpl(cvApiService: CVApiService) : CVRemoteDataStore {

    private val _cvApiService = cvApiService

    override fun getCV(): Flowable<CV> {
        return _cvApiService.getCV(CV_PATH)
            .toFlowable()
    }

}