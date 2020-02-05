package com.antonkrasov.cvapp.data.datastore

import com.antonkrasov.cvapp.data.CVProvider
import com.antonkrasov.cvapp.data.model.CV

/**
 * CV Data Store, which works with the local file system
 */
interface CVLocalDataStore : CVProvider {

    /**
     * Save CV to a local cache
     */
    fun saveCV(cv: CV)

}