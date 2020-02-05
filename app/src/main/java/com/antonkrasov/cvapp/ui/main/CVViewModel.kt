package com.antonkrasov.cvapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.antonkrasov.cvapp.data.model.CV
import com.antonkrasov.cvapp.data.model.ViewModelResult
import com.antonkrasov.cvapp.data.repository.CVRepository
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class CVViewModel @Inject constructor(
    cvRepository: CVRepository
) : ViewModel() {

    private val _cv = MutableLiveData<ViewModelResult<CV>>()
    val cv: LiveData<ViewModelResult<CV>> = _cv

    private var _cvDisposable: Disposable

    init {
        _cv.value = ViewModelResult.loading()
        _cvDisposable = cvRepository.getCV().subscribe(
            {
                _cv.value = ViewModelResult.data(it)
            },
            {
                _cv.value = ViewModelResult.error(it)
            }
        )
    }

    override fun onCleared() {
        _cvDisposable.dispose()
        super.onCleared()
    }
}