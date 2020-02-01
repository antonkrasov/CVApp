package com.antonkrasov.cvapp.data.storage.impl

import android.content.Context
import com.antonkrasov.cvapp.data.storage.AssetsStorage
import com.antonkrasov.cvapp.utils.FileUtils
import io.reactivex.Flowable
import timber.log.Timber
import java.io.InputStream

class AssetsStorageImpl(context: Context) : AssetsStorage {

    private val _context = context

    override fun readAssetIntoString(assetName: String): Flowable<String> {
            return Flowable.fromCallable {
                var rawJsonString: String? = null
                val fileInputStream: InputStream = _context.assets.open(assetName)
                try {
                    rawJsonString = FileUtils.inputStreamIntoString(fileInputStream)
                } catch (ex: Exception) {
                    Timber.e(ex)
                }
                requireNotNull(rawJsonString) { "Cannot read file from assets" }
            }
                .onErrorReturn { "" }
    }

}