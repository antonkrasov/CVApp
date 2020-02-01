package com.antonkrasov.cvapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.antonkrasov.cvapp.R
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.main_fragment.view.*
import javax.inject.Inject

class CVFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<CVViewModel> { viewModelFactory }

    companion object {
        fun newInstance() = CVFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.cv.observe(this, Observer { view.message.setText(it.title) })
    }
}
