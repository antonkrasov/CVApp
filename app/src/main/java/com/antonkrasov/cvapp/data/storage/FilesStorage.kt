package com.antonkrasov.cvapp.data.storage

import io.reactivex.Flowable

/**
 * Work with File system
 */
interface FilesStorage {

    /**
     *@param filePath absolute file path to read from
     * @return The string representation of the file
     */
    fun readFileIntoString(filePath: String) : Flowable<String>

    /**
     * @param filePath absolute file path to save into
     * @param content string content to save
     */
    fun writeStringIntoFile(filePath: String, content: String)

}