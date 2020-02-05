package com.antonkrasov.cvapp.threading

import io.reactivex.Scheduler

/**
 * Provide Schedulers for our RxJava computations...
 */
interface BaseSchedulerProvider {

    fun io(): Scheduler

    fun computation(): Scheduler

    fun ui(): Scheduler

}