package com.rightside.hackaton.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rightside.hackaton.databinding.AdapterActionBinding
import com.rightside.hackaton.model.Action
import java.text.DecimalFormat
import java.text.NumberFormat
import kotlin.random.Random

class ActionAdaper(val actionClickListener : (Action) -> Unit) : RecyclerView.Adapter<ActionAdaper.ActionViewHolder>() {
    lateinit var binding : AdapterActionBinding
    private var actions : List<Action> = ArrayList()
    inner class ActionViewHolder(binding : AdapterActionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(action : Action) {
//            binding.textViewDescription.text = action.description
            binding.textViewId.text = action.id
            binding.textViewSalePrice.text = "R$ " + action.salePrice.toString()
            binding.textViewBuyPrice.text = "R$ " + action.purchasePrice.toString()
            binding.textViewUnity.text = action.quantity.toString()
            binding.textViewName.text = action.name
            binding.textViewUnity2.text = action.actualYear
            binding.textViewUnityMiddleYear.text = action.middleYear
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActionViewHolder {
        binding = AdapterActionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ActionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ActionViewHolder, position: Int) {
        val action = actions[position]
        holder.bind(action)

        holder.itemView.setOnClickListener {
            actionClickListener(action)
        }
    }

    override fun getItemCount(): Int = actions.size

    fun updateActions(actions : List<Action>) {
        this.actions = actions
        notifyDataSetChanged()
    }
}