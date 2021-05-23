package com.rightside.hackaton.view.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.rightside.hackaton.R
import com.rightside.hackaton.databinding.FragmentProducerDetailsBinding
import com.rightside.hackaton.model.Action
import com.rightside.hackaton.model.Producer
import com.rightside.hackaton.presenter.ProducerDetailsPresenter
import com.rightside.hackaton.util.DotsIndicatorDecoration
import com.rightside.hackaton.view.adapter.ActionDetailsAdapter
import com.rightside.hackaton.view.contracts.ProducerDetailsContract
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import org.koin.android.ext.android.inject


class ProducerDetailsFragment : Fragment(R.layout.fragment_producer_details) , ProducerDetailsContract.View{
    lateinit var binding : FragmentProducerDetailsBinding
    private val args : ProducerDetailsFragmentArgs by navArgs()
    private val actionAdapter by lazy { ActionDetailsAdapter(::actionClickListener) }
    override val presenter: ProducerDetailsPresenter by inject()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProducerDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.view = this
        presenter.lifecycle = lifecycle
        presenter.init()
        binding.carousel.registerLifecycle(lifecycle)
        val producer = args.producer
        val list = mutableListOf<CarouselItem>()
        producer.photos.map { list.add(CarouselItem(it,"")) }
        binding.carousel.setData(list)
        binding.recyclerViewProducerActives.apply {
            adapter = actionAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(DotsIndicatorDecoration(10, 20, 150, Color.GRAY, Color.BLACK))
        }
        presenter.getAllActivesByIdProducer(producerId = producer.id)
        bind(producer)
    }

    override fun showLoading() {
        //todo
    }

    override fun hideLoading() {
        //todo
    }

    override fun updateActions(it: List<Action>) {
        actionAdapter.updateActions(it)
    }

    private fun actionClickListener(action: Action) {
        findNavController().navigate(ProducerDetailsFragmentDirections.actionProducerDetailsFragmentToActionDetailsFragment(action))
    }
    private fun bind(producer : Producer) {
        binding.textViewProducerCompanyName.text = producer.companyName
        binding.textViewProducerName.text = producer.name
    }


}