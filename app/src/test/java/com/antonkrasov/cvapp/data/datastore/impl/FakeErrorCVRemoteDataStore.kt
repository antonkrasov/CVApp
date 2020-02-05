package com.antonkrasov.cvapp.data.datastore.impl

import com.antonkrasov.cvapp.data.datastore.CVRemoteDataStore
import com.antonkrasov.cvapp.data.model.CV
import com.antonkrasov.cvapp.fakeCV
import io.reactivex.Flowable

class FakeErrorCVRemoteDataStore : CVRemoteDataStore {

    override fun getCV(): Flowable<CV> {
        return Flowable.just(fakeCV)
    }

}