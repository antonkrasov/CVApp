package com.antonkrasov.cvapp.data.datastore.impl

import android.content.Context
import com.antonkrasov.cvapp.data.model.CV
import com.antonkrasov.cvapp.data.datastore.CVLocalDataStore
import io.reactivex.Flowable

class CVLocalDataStoreImpl(context: Context) : CVLocalDataStore {

    private val context: Context

    init {
        this.context = context
    }

    override fun getCV(): Flowable<CV> {
        return Flowable.just(CV(title = "Test Local CV"))
    }

    override fun saveCV(cv: CV) {

    }

}