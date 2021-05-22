package com.rightside.hackaton.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.rightside.hackaton.R
import com.rightside.hackaton.databinding.FragmentActionDetailsBinding
import com.rightside.hackaton.extensions.loadImage
import com.rightside.hackaton.model.Action
import com.rightside.hackaton.view.contracts.ActionDetailsContract


class ActionDetailsFragment : Fragment(R.layout.fragment_action_details), ActionDetailsContract.View {
   private lateinit var binding : FragmentActionDetailsBinding
    override val presenter: ActionDetailsContract.Presenter by inject()
    private val args : ActionDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentActionDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val action = args.action
        setFields(action)
        renderGraph()

        binding.materialButton.setOnClickListener {

        }
    }

    private fun setFields(action: Action) {
        binding.textViewBuyPrice.text = action.purchasePrice.toString()
        binding.textViewDescription.text = action.description
        binding.textViewId.text = action.id
        binding.textViewName.text = action.producer?.name
        binding.textViewUnity.text = action.quantity.toString()
        action.producer?.profilePicture?.let { binding.imageViewActionOwnerPicture.loadImage(it) }
        binding.textViewSalePrice.text = action.salePrice.toString()
    }

    private fun renderGraph() {
        val entries: MutableList<BarEntry> = ArrayList()
        entries.add(BarEntry(0f, 4f))
        entries.add(BarEntry(1f, 8f))
        entries.add(BarEntry(2f, 10f))
        entries.add(BarEntry(5f, 13f))
        entries.add(BarEntry(6f, 14f))
        entries.add(BarEntry(6f, 16f))
        val set = BarDataSet(entries, "BarDataSet")
        val xAxisLabel: ArrayList<String> = ArrayList()
        xAxisLabel.add("2009-2011")
        xAxisLabel.add("2011-2013")
        xAxisLabel.add("2013-2015")
        xAxisLabel.add("2015-2017")
        xAxisLabel.add("2017-2019")
        xAxisLabel.add("2019-2021")
        xAxisLabel.add("2019-2021")
        val xAxis = binding.graph.xAxis
        val data = BarData(set)
        data.barWidth = 0.9f
        binding.graph.data = data
        binding.graph.setFitBars(true)
        xAxis.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return xAxisLabel[value.toInt()]
            }
        }

    }



}