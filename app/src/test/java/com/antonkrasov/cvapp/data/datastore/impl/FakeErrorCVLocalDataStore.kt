package com.antonkrasov.cvapp.data.datastore.impl

import com.antonkrasov.cvapp.data.datastore.CVLocalDataStore
import com.antonkrasov.cvapp.data.model.CV
import io.reactivex.Flowable

class FakeErrorCVLocalDataStore : CVLocalDataStore {

    override fun saveCV(cv: CV) {

    }

    override fun getCV(): Flowable<CV> {
        return Flowable.error(IllegalStateException("Test error"))
    }

}