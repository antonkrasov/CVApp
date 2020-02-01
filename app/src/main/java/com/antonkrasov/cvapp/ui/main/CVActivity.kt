package com.antonkrasov.cvapp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.antonkrasov.cvapp.R

class CVActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CVFragment.newInstance())
                .commitNow()
        }
    }

}
