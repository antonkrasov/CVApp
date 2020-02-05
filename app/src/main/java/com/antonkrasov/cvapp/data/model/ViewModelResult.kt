package com.antonkrasov.cvapp.data.model

/**
 * Generic class to be used to deliver result from ViewModel to UI
 */
class ViewModelResult<T>(val status: Status, val data: T? = null, val error: Throwable? = null) {

    companion object {

        fun <T> loading(): ViewModelResult<T> {
            return ViewModelResult(Status.LOADING)
        }

        fun <T> error(error: Throwable): ViewModelResult<T> {
            return ViewModelResult(Status.ERROR, error = error)
        }

        fun <T> data(data: T): ViewModelResult<T> {
            return ViewModelResult(Status.SUCCESS, data = data)
        }

    }

}

enum class Status {
    SUCCESS,
    LOADING,
    ERROR
}