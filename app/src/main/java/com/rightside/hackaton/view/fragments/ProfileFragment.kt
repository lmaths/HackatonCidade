package com.rightside.hackaton.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.rightside.hackaton.R
import com.rightside.hackaton.databinding.FragmentProfileBinding
import com.rightside.hackaton.extensions.loadImage
import com.rightside.hackaton.model.Action
import com.rightside.hackaton.model.User
import com.rightside.hackaton.presenter.ProfilePresenter
import com.rightside.hackaton.view.adapter.ActionAdaper
import com.rightside.hackaton.view.contracts.ProfileContract
import org.koin.android.ext.android.inject

class ProfileFragment : Fragment(), ProfileContract.View {

    lateinit var binding : FragmentProfileBinding

    override fun moveUserToLogin() {

    }



    override val presenter: ProfilePresenter by inject()
    private val actionsAdapter by lazy { ActionAdaper(::onActionClickListener) }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.view = this
        presenter.lifecycle = lifecycle
        presenter.init()
        binding.recyclerViewActionsHistory.apply {
            adapter = actionsAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun updateData(userdata: User, actions: List<Action>) {
        binding.textViewUserName.text = userdata.name
        binding.imageViewUserProfile.loadImage(userdata.pictureUrl)
        actionsAdapter.updateActions(actions)
    }

    private fun onActionClickListener(action: Action) {
        //vender
    }


}