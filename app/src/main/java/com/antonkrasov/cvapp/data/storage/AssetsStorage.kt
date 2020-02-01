package com.antonkrasov.cvapp.data.storage

import io.reactivex.Flowable

interface AssetsStorage {

    fun readAssetIntoString(assetName: String) : Flowable<String>

}