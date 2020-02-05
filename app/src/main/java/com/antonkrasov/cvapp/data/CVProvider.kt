package com.antonkrasov.cvapp.data

import com.antonkrasov.cvapp.data.model.CV
import io.reactivex.Flowable

/**
 * Provide our CV instance
 */
interface CVProvider {

    /**
     * @return our CV
     */
    fun getCV() : Flowable<CV>

}