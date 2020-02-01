package com.antonkrasov.cvapp.data.datastore.impl

import com.antonkrasov.cvapp.CV_PATH
import com.antonkrasov.cvapp.data.CVApiService
import com.antonkrasov.cvapp.data.model.CV
import com.antonkrasov.cvapp.data.datastore.CVRemoteDataStore
import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

class CVRemoteDataStoreImpl(cvApiService: CVApiService) : CVRemoteDataStore {

    private val _cvApiService = cvApiService

    override fun getCV(): Flowable<CV> {
        return _cvApiService.getCV(CV_PATH)
            .toFlowable()
//            .delay(3, TimeUnit.SECONDS) // Just for tests
    }

}