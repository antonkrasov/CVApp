package com.antonkrasov.cvapp.utils

import android.os.Looper

fun isMainThread() : Boolean {
    return Looper.myLooper() == Looper.getMainLooper()
}