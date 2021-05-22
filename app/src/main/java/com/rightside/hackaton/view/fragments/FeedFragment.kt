package com.rightside.hackaton.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.rightside.hackaton.R
import com.rightside.hackaton.presenter.HelloWorldPresenter
import com.rightside.hackaton.view.contracts.FeedContract
import org.koin.android.ext.android.inject


class FeedFragment : Fragment(R.layout.fragment_feed), FeedContract.View {
    override val presenter: HelloWorldPresenter by inject()
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.lifecycle = lifecycle
        presenter.view = this
        presenter.init()
    }


    override fun helloWorld(value: String) {
        Toast.makeText(requireContext(), value, Toast.LENGTH_SHORT).show()
    }

}