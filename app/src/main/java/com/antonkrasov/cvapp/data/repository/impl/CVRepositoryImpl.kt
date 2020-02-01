package com.antonkrasov.cvapp.data.repository.impl

import com.antonkrasov.cvapp.data.model.CV
import com.antonkrasov.cvapp.data.datastore.CVLocalDataStore
import com.antonkrasov.cvapp.data.datastore.CVRemoteDataStore
import com.antonkrasov.cvapp.data.repository.CVRepository
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CVRepositoryImpl(localDataStore: CVLocalDataStore, remoteDataStore: CVRemoteDataStore) :
    CVRepository {

    private val _localDataStore: CVLocalDataStore = localDataStore
    private val _remoteDataStore: CVRemoteDataStore = remoteDataStore

    override fun getCV(): Flowable<CV> {
        return _remoteDataStore.getCV()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}