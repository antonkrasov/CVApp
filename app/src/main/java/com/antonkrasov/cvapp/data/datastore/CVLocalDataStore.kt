package com.antonkrasov.cvapp.data.datastore

import com.antonkrasov.cvapp.data.model.CV
import io.reactivex.Flowable

interface CVLocalDataStore {

    fun getCV(): Flowable<CV>

    fun saveCV(cv: CV)

}