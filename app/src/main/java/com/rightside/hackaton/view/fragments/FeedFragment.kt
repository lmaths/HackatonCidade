package com.rightside.hackaton.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.lottie.LottieAnimationView
import com.rightside.hackaton.R
import com.rightside.hackaton.databinding.FragmentFeedBinding
import com.rightside.hackaton.extensions.invisibleUI
import com.rightside.hackaton.extensions.visibleUI
import com.rightside.hackaton.model.Action
import com.rightside.hackaton.presenter.FeedPresenter
import com.rightside.hackaton.view.adapter.ActionAdaper
import com.rightside.hackaton.view.contracts.FeedContract
import org.koin.android.ext.android.inject


class FeedFragment : Fragment(R.layout.fragment_feed), FeedContract.View {
    override val presenter: FeedPresenter by inject()
    private lateinit var binding : FragmentFeedBinding
    private val actionAdapter by lazy { ActionAdaper(::actionClickListener) }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFeedBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.lifecycle = lifecycle
        presenter.view = this
        presenter.init()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = actionAdapter
        }
    }

    override fun showLogin() {
        findNavController().navigate(FeedFragmentDirections.actionFeedFragmentToLoginFragment())
    }

    override fun showLoading() {
        binding.animationView.visibleUI()
    }

    override fun updateFeed(it: List<Action>) {
        actionAdapter.updateActions(it)
    }

    override fun showEmptyFeed() {
        // todo criar view para quando não tiver nenhuma ação disponivel
    }

    override fun hideLoading() {
        binding.animationView.invisibleUI()
    }

    override fun showDetails(action: Action) {
       findNavController().navigate(FeedFragmentDirections.actionFeedFragmentToActionDetailsFragment(action))
    }

    override fun moveToLogin() {
        findNavController().navigate(FeedFragmentDirections.actionFeedFragmentToLoginFragment())
    }

    private fun actionClickListener(action: Action) {
        presenter.moveToDetails(action)
    }




}