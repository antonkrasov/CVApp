package com.antonkrasov.cvapp.data.datastore.impl

import android.content.Context
import com.antonkrasov.cvapp.data.model.CV
import com.antonkrasov.cvapp.data.datastore.CVLocalDataStore
import com.antonkrasov.cvapp.utils.FileUtils
import com.google.gson.Gson
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.io.*

class CVLocalDataStoreImpl(context: Context, gson: Gson) : CVLocalDataStore {

    private val _context: Context = context
    private val _gson: Gson = gson

    override fun getCV(): Flowable<CV> {
        return Flowable.fromCallable {
            var rawJsonString: String? = null
            val fileInputStream: InputStream = _context.assets.open("cv.json")
            try {
                rawJsonString = FileUtils.inputStreamIntoString(fileInputStream)
            } catch (ex: Exception) {
                Timber.e(ex)
            }
            requireNotNull(rawJsonString) { "Cannot read file from assets" }
            val cv = _gson.fromJson(rawJsonString, CV::class.java)
            cv
        }
            .onErrorReturn { ex: Throwable? ->
                Timber.e(ex)
                null
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun saveCV(cv: CV) {

    }

}