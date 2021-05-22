package com.rightside.hackaton.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rightside.hackaton.R
import com.rightside.hackaton.databinding.FragmentProfileBinding
import com.rightside.hackaton.presenter.ProfilePresenter
import com.rightside.hackaton.view.contracts.ProfileContract
import org.koin.android.ext.android.inject

class ProfileFragment : Fragment(), ProfileContract.View {

    lateinit var binding : FragmentProfileBinding
    override val presenter: ProfilePresenter by inject()

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
    }


}