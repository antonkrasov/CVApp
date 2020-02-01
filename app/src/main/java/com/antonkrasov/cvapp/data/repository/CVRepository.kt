package com.antonkrasov.cvapp.data.repository

import com.antonkrasov.cvapp.data.model.CV
import io.reactivex.Flowable

interface CVRepository {

    fun getCV(): Flowable<CV>

}