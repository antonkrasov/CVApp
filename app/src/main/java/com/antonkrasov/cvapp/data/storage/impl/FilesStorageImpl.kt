package com.antonkrasov.cvapp.data.storage.impl

import com.antonkrasov.cvapp.data.storage.FilesStorage
import io.reactivex.Flowable
import java.io.BufferedReader
import java.io.File

class FilesStorageImpl : FilesStorage {

    override fun readFileIntoString(filePath: String): Flowable<String> {
        // 1. check if file exists and if we can read it
        // 2. if yes, read and return content as string

        val file = File(filePath)
        if (file.exists().not() || file.canRead().not()) {
            return Flowable.just("")
        }

        return Flowable.fromCallable {
            val content = file.bufferedReader().use(BufferedReader::readText)
            content
        }
            .onErrorReturn { "" }
    }

    override fun writeStringIntoFile(filePath: String, content: String) {
        File(filePath).bufferedWriter().use {
            it.write(content)
        }
    }

}