package com.rightside.hackaton.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rightside.hackaton.databinding.AdapterActionDetailsBinding
import com.rightside.hackaton.model.Action

class ActionDetailsAdapter(val actionClickListener : (Action) -> Unit ) : RecyclerView.Adapter<ActionDetailsAdapter.ActionDetailsViewHolder>() {

    private lateinit var binding : AdapterActionDetailsBinding
    private var actions : List<Action> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActionDetailsViewHolder {
        binding = AdapterActionDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ActionDetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ActionDetailsViewHolder, position: Int) {
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

    inner class ActionDetailsViewHolder(binding : AdapterActionDetailsBinding) : RecyclerView.ViewHolder(binding.root) {
         fun bind(action : Action) {
            binding.textViewName.text = action.producer?.name
            binding.textViewUnity.text = action.quantity.toString()
            binding.textViewBuyPrice.text = "R$ ${action.purchasePrice.toString()}"
            binding.textViewSalePrice.text = "R$ ${action.salePrice.toString()}"
            binding.textViewId.text = action.id
             binding.textViewUnity2.text = (0..500).random().toString()
        }
    }
}