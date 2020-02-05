package com.antonkrasov.cvapp.data.datastore.impl

import com.antonkrasov.cvapp.data.datastore.CVLocalDataStore
import com.antonkrasov.cvapp.data.model.CV
import com.antonkrasov.cvapp.fakeCV
import io.reactivex.Flowable

class FakeValidCVLocalDataStore : CVLocalDataStore {

    private var _cv = fakeCV

    override fun saveCV(cv: CV) {
        _cv = cv
    }

    override fun getCV(): Flowable<CV> {
        return Flowable.just(_cv)
    }

}