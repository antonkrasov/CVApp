package com.antonkrasov.cvapp.data.datastore

import com.antonkrasov.cvapp.data.model.CV
import io.reactivex.Flowable

interface CVRemoteDataStore {

    fun getCV(): Flowable<CV>

}