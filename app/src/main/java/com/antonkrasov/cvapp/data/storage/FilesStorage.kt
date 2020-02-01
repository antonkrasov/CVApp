package com.antonkrasov.cvapp.data.storage

import io.reactivex.Flowable

interface FilesStorage {

    fun readFileIntoString(filePath: String) : Flowable<String>

    fun writeStringIntoFile(filePath: String, content: String)

}