package com.antonkrasov.cvapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.antonkrasov.cvapp.R
import com.antonkrasov.cvapp.data.model.CV
import com.antonkrasov.cvapp.data.model.Status
import com.antonkrasov.cvapp.data.model.ViewModelResult
import com.antonkrasov.cvapp.ui.main.cvadapter.CVAdapter
import com.antonkrasov.cvapp.ui.main.cvadapter.SectionsBuilder
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.main_fragment.view.*
import java.net.UnknownHostException
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

        view.recyclerView.layoutManager = LinearLayoutManager(context)
        val cvAdapter = CVAdapter(layoutInflater, SectionsBuilder())
        view.recyclerView.adapter = cvAdapter

        viewModel.cv.observe(this, Observer { render(it) })
    }

    private fun render(result: ViewModelResult<CV>) {
        when(result.status) {
            Status.LOADING -> renderLoadingState()
            Status.SUCCESS -> renderIdleState(result.data!!)
            Status.ERROR -> renderErrorState(result.error!!)
        }
    }

    private fun renderLoadingState() {
        check(view != null)

        view!!.progress.visibility = View.VISIBLE
        view!!.recyclerView.visibility = View.GONE
        view!!.error.visibility = View.GONE
    }

    private fun renderIdleState(cv: CV) {
        check(view != null)

        view!!.progress.visibility = View.GONE
        view!!.recyclerView.visibility = View.VISIBLE
        view!!.error.visibility = View.GONE

        val cvAdapter = view!!.recyclerView.adapter as CVAdapter
        cvAdapter.setCV(cv)
    }

    private fun renderErrorState(ex: Throwable) {
        check(view != null)

        view!!.progress.visibility = View.GONE
        view!!.recyclerView.visibility = View.GONE
        view!!.error.visibility = View.VISIBLE

        view!!.error.text = getTextForError(ex)
    }

    private fun getTextForError(ex: Throwable) : String {
        if (ex is UnknownHostException) {
            return getString(R.string.error_no_internet_connection)
        }

        return getString(R.string.error_unknown)
    }

}
