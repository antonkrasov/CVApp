package com.antonkrasov.cvapp.data.repository.impl

import com.antonkrasov.cvapp.data.datastore.impl.FakeErrorCVLocalDataStore
import com.antonkrasov.cvapp.data.datastore.impl.FakeErrorCVRemoteDataStore
import com.antonkrasov.cvapp.data.datastore.impl.FakeValidCVLocalDataStore
import com.antonkrasov.cvapp.data.datastore.impl.FakeValidCVRemoteDataStore
import com.antonkrasov.cvapp.di.ThreadingModule
import com.antonkrasov.cvapp.fakeCV
import io.reactivex.disposables.CompositeDisposable
import org.junit.Assert.assertEquals
import org.junit.Test

class CVRepositoryImplTest {

    private val compositeDisposable = CompositeDisposable()

    private val schedulerProvider = ThreadingModule.provideScheduleProvider()

    @Test
    fun `Correct CV from both sources`() {
        val cvRepository = CVRepositoryImpl(
            FakeValidCVLocalDataStore(),
            FakeValidCVRemoteDataStore(),
            compositeDisposable,
            schedulerProvider
        )

        val cv = cvRepository.getCV().blockingFirst()
        assertEquals(fakeCV, cv)
    }

    @Test
    fun `Correct CV with error from the local data store`() {
        val cvRepository = CVRepositoryImpl(
            FakeErrorCVLocalDataStore(),
            FakeValidCVRemoteDataStore(),
            compositeDisposable,
            schedulerProvider
        )

        val cv = cvRepository.getCV().blockingFirst()
        assertEquals(fakeCV, cv)
    }

    @Test
    fun `Correct CV with error from the remote data store`() {
        val cvRepository = CVRepositoryImpl(
            FakeValidCVLocalDataStore(),
            FakeErrorCVRemoteDataStore(),
            compositeDisposable,
            schedulerProvider
        )

        val cv = cvRepository.getCV().blockingFirst()
        assertEquals(fakeCV, cv)
    }

}