package com.antonkrasov.cvapp.data.datastore.impl

import android.content.Context
import com.antonkrasov.cvapp.CV_FILE_NAME
import com.antonkrasov.cvapp.data.datastore.CVLocalDataStore
import com.antonkrasov.cvapp.data.model.CV
import com.antonkrasov.cvapp.data.storage.AssetsStorage
import com.antonkrasov.cvapp.data.storage.FilesStorage
import com.google.gson.Gson
import io.reactivex.Flowable
import java.io.File

class CVLocalDataStoreImpl(
    context: Context,
    gson: Gson,
    assetsStorage: AssetsStorage,
    filesStorage: FilesStorage
) : CVLocalDataStore {

    private val _context: Context = context
    private val _gson: Gson = gson
    private val _assetsStorage = assetsStorage
    private val _filesStorage = filesStorage

    private val _cvLocalFile: File
        get() = File(_context.filesDir, CV_FILE_NAME)

    override fun getCV(): Flowable<CV> {
        // 1. try to read from files
        // 2. if we got empty string, read from assets
        // 3. use gson to build an obj from string

        return _filesStorage.readFileIntoString(_cvLocalFile.absolutePath)
            .flatMap {
                if (it.isEmpty()) _assetsStorage.readAssetIntoString(CV_FILE_NAME) else Flowable.just(
                    it
                )
            }
            .map {
                val cv = _gson.fromJson(it, CV::class.java)
                cv
            }
    }

    /**
     * Should be called from another thread...
     * Maybe should be Flowable?
     */
    override fun saveCV(cv: CV) {
        val jsonString = _gson.toJson(cv)
        _filesStorage.writeStringIntoFile(_cvLocalFile.absolutePath, jsonString)
    }

}