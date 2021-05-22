package com.rightside.hackaton.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rightside.hackaton.databinding.AdapterProducerBinding
import com.rightside.hackaton.model.Producer

class ProducerAdapter(val producerClickListener : (Producer) -> Unit) : RecyclerView.Adapter<ProducerAdapter.ProducerViewHolder>() {
    private var producers: List<Producer> = ArrayList()
    private lateinit var binding: AdapterProducerBinding
    inner class ProducerViewHolder(binding : AdapterProducerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(producer: Producer) {
            binding.textViewProducerName.text = producer.name
            binding.textViewProducerAddress.text = "endereço"
            binding.textViewProducerQuantity.text = producer.uuid
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProducerViewHolder {
        binding = AdapterProducerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProducerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProducerViewHolder, position: Int) {
        val producer = producers[position]
        holder.bind(producer)
        holder.itemView.setOnClickListener { producerClickListener(producer) }
    }

    override fun getItemCount(): Int = producers.size

    fun updateProducers(producers : List<Producer>) {
        this.producers = producers
        notifyDataSetChanged()
    }

}