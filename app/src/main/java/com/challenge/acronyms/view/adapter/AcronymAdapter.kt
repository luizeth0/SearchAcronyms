package com.challenge.acronyms.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.challenge.acronyms.databinding.ItemAcronymBinding
import com.challenge.acronyms.model.AcronymResponseItem
import com.challenge.acronyms.model.Lf
import com.challenge.acronyms.utils.UIState

class AcronymAdapter(
    private val itemSet: MutableList<Lf> = mutableListOf(),
    private val onItemClick: (item: Lf) -> Unit
): RecyclerView.Adapter<AcronymViewHolder>() {

    fun updateItems(newItems: List<Lf>) {

        if (itemSet!=newItems) {
            itemSet.clear()
            itemSet.addAll(newItems)

            notifyDataSetChanged()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcronymViewHolder =
        AcronymViewHolder(
            ItemAcronymBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = itemSet.size

    override fun onBindViewHolder(holder: AcronymViewHolder, position: Int) =
        holder.bind(itemSet[position], onItemClick)
}

class AcronymViewHolder(
    private val binding: ItemAcronymBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Lf, onItemClick: (item: Lf) -> Unit) {

        binding.meaning.text = item.lf
        binding.freq.text = item.freq.toString()
        binding.since.text = item.since.toString()

        itemView.setOnClickListener {
            item.let(onItemClick)
        }
    }

}
