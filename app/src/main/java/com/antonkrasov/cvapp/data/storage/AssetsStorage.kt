package com.antonkrasov.cvapp.data.storage

import io.reactivex.Flowable

/**
 * Work with assets
 */
interface AssetsStorage {

    /**
     * @param assetName name of the asset to read
     * @return The string representation of the asset
     */
    fun readAssetIntoString(assetName: String) : Flowable<String>

}