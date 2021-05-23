package com.rightside.hackaton.view.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.rightside.hackaton.R
import com.rightside.hackaton.databinding.FragmentActionDetailsBinding
import com.rightside.hackaton.extensions.loadImage
import com.rightside.hackaton.extensions.loadImageCircle
import com.rightside.hackaton.model.Action
import com.rightside.hackaton.presenter.ActionDetailsPresenter
import com.rightside.hackaton.view.contracts.ActionDetailsContract
import org.koin.android.ext.android.inject


class ActionDetailsFragment : Fragment(R.layout.fragment_action_details), ActionDetailsContract.View {
   private lateinit var binding : FragmentActionDetailsBinding
    override val presenter: ActionDetailsPresenter by inject()
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
        presenter.view = this
        presenter.lifecycle = lifecycle
        presenter.init()
        val action = args.action
        setFields(action)
        renderGraph()

        binding.materialButton.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setMessage("Tem certeza que deseja comprar a ação?")
                .setPositiveButton("Comprar",
                    DialogInterface.OnClickListener { dialog, id ->
                        dialog.dismiss()
                        presenter.buyAction(action)
                        presenter.init()
                        Toast.makeText(requireContext(), "Compra realizada com sucesso.", Toast.LENGTH_SHORT).show()
                    })
                .setNegativeButton("Cancelar",
                    DialogInterface.OnClickListener { dialog, id ->
                        dialog.dismiss()
                    })
            builder.create().show()
        }
        binding.btnSeeActionOwnerProfile.setOnClickListener {
            action.producer?.let {
                findNavController().navigate(ActionDetailsFragmentDirections.actionActionDetailsFragmentToProducerDetailsFragment(it))
            }

        }
    }

    private fun setFields(action: Action) {
        binding.textViewDescription.text = action.description
        binding.textViewId.text = action.id
        binding.textViewName.text = action.producer?.name
        binding.textViewUnity.text = action.quantity.toString()
        action.producer?.profilePicture?.let { binding.imageViewActionOwnerPicture.loadImageCircle(it) }
        binding.textViewSalePrice.text = "R$ ${action.salePrice.toString()}"
        binding.textViewActualYear.text = action.actualYear
        binding.textViewMiddleYearSlaughter.text = action.middleYear
    }

    private fun renderGraph() {
        val entries: MutableList<BarEntry> = ArrayList()
        entries.add(BarEntry(0f, 30f))
        entries.add(BarEntry(1f, 50f))
        entries.add(BarEntry(2f, 100f))
        entries.add(BarEntry(3f, 150f))
        entries.add(BarEntry(4f, 200f))
        val set = BarDataSet(entries, "Peso")
        val xAxisLabel: ArrayList<String> = ArrayList()
        xAxisLabel.add("1")
        xAxisLabel.add("2")
        xAxisLabel.add("3")
        xAxisLabel.add("4")
        xAxisLabel.add("5")
        val xAxis = binding.graph.xAxis
        val data = BarData(set)
        data.barWidth = 0.9f
        binding.graph.data = data
        binding.graph.description.text = ""
        binding.graph.setFitBars(true)
        xAxis.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return xAxisLabel[value.toInt()]
            }
        }

    }



}