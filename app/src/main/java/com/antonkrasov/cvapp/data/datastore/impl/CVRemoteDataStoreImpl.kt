package com.antonkrasov.cvapp.data.datastore.impl

import com.antonkrasov.cvapp.data.model.CV
import com.antonkrasov.cvapp.data.datastore.CVRemoteDataStore
import io.reactivex.Flowable

class CVRemoteDataStoreImpl : CVRemoteDataStore {

    override fun getCV(): Flowable<CV> {
        return Flowable.just(null)
    }

}