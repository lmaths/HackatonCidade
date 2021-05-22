package com.rightside.hackaton.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rightside.hackaton.R
import com.rightside.hackaton.databinding.FragmentProducerBinding
import com.rightside.hackaton.extensions.invisibleUI
import com.rightside.hackaton.extensions.visibleUI
import com.rightside.hackaton.model.Producer
import com.rightside.hackaton.presenter.ProducerPresenter
import com.rightside.hackaton.view.adapter.ProducerAdapter
import com.rightside.hackaton.view.contracts.ProducerContract
import org.koin.android.ext.android.inject


class ProducerFragment : Fragment(R.layout.fragment_producer), ProducerContract.View {
    private lateinit var binding : FragmentProducerBinding
    private val producerAdapter by lazy { ProducerAdapter(::producerClickListener) }
    override val presenter: ProducerPresenter by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProducerBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.view = this
        presenter.lifecycle = lifecycle
        presenter.init()
        binding.recyclerViewProducers.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )
        binding.recyclerViewProducers.apply {
            adapter =  producerAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false )
        }
    }

    override fun showLoading() {
        binding.animationView.visibleUI()
    }

    override fun showProducers(it: List<Producer>) {
        binding.recyclerViewProducers.visibleUI()
        producerAdapter.updateProducers(it)
    }

    override fun showEmptyProducers() {

    }

    override fun hideLoading() {
        binding.animationView.invisibleUI()
    }

    override fun moveToProducerDetails(producer: Producer) {
        findNavController().navigate(ProducerFragmentDirections.actionProducerFragmentToProducerDetailsFragment(producer))
    }

    override fun moveToLogin() {
        findNavController().navigate(ProducerFragmentDirections.actionProducerFragmentToLoginFragment())
    }

    private fun producerClickListener(producer: Producer) {
        presenter.moveToProducerDetails(producer)
    }

    override fun onResume() {
        super.onResume()
        binding.recyclerViewProducers.invisibleUI()
    }
}