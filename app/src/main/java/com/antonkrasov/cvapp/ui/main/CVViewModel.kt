package com.antonkrasov.cvapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.antonkrasov.cvapp.data.model.CV
import com.antonkrasov.cvapp.data.repository.CVRepository
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class CVViewModel @Inject constructor(
    cvRepository: CVRepository
) : ViewModel() {

    private val _cv = MutableLiveData<CV>()
    val cv: LiveData<CV> = _cv

    private var _cvDisposable: Disposable

    init {
        _cvDisposable = cvRepository.getCV().subscribe {
             _cv.value = it
         }
    }

    override fun onCleared() {
        _cvDisposable.dispose()
        super.onCleared()
    }
}