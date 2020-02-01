package com.antonkrasov.cvapp.data.repository.impl

import com.antonkrasov.cvapp.data.model.CV
import com.antonkrasov.cvapp.data.datastore.CVLocalDataStore
import com.antonkrasov.cvapp.data.datastore.CVRemoteDataStore
import com.antonkrasov.cvapp.data.repository.CVRepository
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber

class CVRepositoryImpl(
    localDataStore: CVLocalDataStore,
    remoteDataStore: CVRemoteDataStore,
    compositeDisposable: CompositeDisposable
) :
    CVRepository {

    private val _cvSubject: BehaviorSubject<CV> = BehaviorSubject.create()

    init {
        val localCVDisable = localDataStore.getCV().subscribe { cv: CV ->
            Timber.i("We got local CV(%d)", cv.lastUpdate)
            _cvSubject.onNext(cv)
        }

        val removeCVDisposable = remoteDataStore.getCV()
            .doOnNext { cv: CV ->
                Timber.i("We got remote CV(%d)", cv.lastUpdate)
                val currentCV = _cvSubject.value

                // there is not need to save CV, if we have the same version...
                if (currentCV != cv) {
                    Timber.i("Remote and local CVs are not the same, save CV")
                    localDataStore.saveCV(cv)
                }
            } // let's emit next item only if we have new CV
            .filter { cv: CV ->
                val currentCV = _cvSubject.value
                currentCV != cv
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { cv: CV ->
                Timber.i("Emit Remote CV(%d)", cv.lastUpdate)
                _cvSubject.onNext(cv)
            }

        compositeDisposable.add(localCVDisable)
        compositeDisposable.add(removeCVDisposable)
    }

    override fun getCV(): Flowable<CV> {
        return _cvSubject.toFlowable(BackpressureStrategy.LATEST)
    }

}