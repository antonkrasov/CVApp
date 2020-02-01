package com.antonkrasov.cvapp.data.repository.impl

import com.antonkrasov.cvapp.data.model.CV
import com.antonkrasov.cvapp.data.datastore.CVLocalDataStore
import com.antonkrasov.cvapp.data.datastore.CVRemoteDataStore
import com.antonkrasov.cvapp.data.repository.CVRepository
import io.reactivex.Flowable

class CVRepositoryImpl(localDataStore: CVLocalDataStore, remoteDataStore: CVRemoteDataStore) :
    CVRepository {

    private val localDataStore: CVLocalDataStore

    init {
        this.localDataStore = localDataStore
    }

    override fun getCV(): Flowable<CV> {
        return localDataStore.getCV()
    }

}