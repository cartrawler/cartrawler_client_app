package com.hisham.ctintegrationsample.landingSelector

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cartrawler.core.base.USPDisplayType
import com.hisham.ctintegrationsample.R
import com.hisham.ctintegrationsample.databinding.SelectorItemBinding

class SelectorAdapter(private val onClick: (String) -> Unit) : ListAdapter<USPDisplayType, SelectorAdapter.SelectorViewHolder>(selectorDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectorAdapter.SelectorViewHolder {
        val binding = SelectorItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return SelectorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SelectorAdapter.SelectorViewHolder, position: Int) {
        val landingStyle = getItem(position)
        holder.bind(landingStyle) {
            onClick(it)
        }
    }

    inner class SelectorViewHolder(private val binding: SelectorItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(landingStyle: USPDisplayType, onClick: (String) -> Unit) {
            val context = binding.root.context

            if (landingStyle.name == USPDisplayType.DEFAULT_STYLE.name) {
                binding.itemText.text = context.getString(R.string.landing_style_default)
            } else {
                binding.itemText.text = context.getString(R.string.landing_style_checkmark)
            }

            binding.itemText.setOnClickListener {
                onClick(landingStyle.name)
            }
        }
    }
}

private val selectorDiff = object : DiffUtil.ItemCallback<USPDisplayType>() {
    override fun areItemsTheSame(oldItem: USPDisplayType, newItem: USPDisplayType): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: USPDisplayType, newItem: USPDisplayType): Boolean {
        return false
    }
}