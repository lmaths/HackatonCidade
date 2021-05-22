package com.rightside.hackaton.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rightside.hackaton.databinding.AdapterActionBinding
import com.rightside.hackaton.model.Action

class ActionAdaper(val actionClickListener : (Action) -> Unit) : RecyclerView.Adapter<ActionAdaper.ActionViewHolder>() {
    lateinit var binding : AdapterActionBinding
    private var actions : List<Action> = ArrayList()
    inner class ActionViewHolder(binding : AdapterActionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(action : Action) {
            binding.textViewDescription.text = action.description
            binding.textViewId.text = action.id
            binding.textViewSalePrice.text = action.salePrice.toString()
            binding.textViewBuyPrice.text = action.purchasePrice.toString()
            binding.textViewUnity.text = action.quantity.toString()
            binding.textViewName.text = action.name.toString()
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