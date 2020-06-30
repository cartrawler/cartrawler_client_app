package com.hisham.ctintegrationsample.searchlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hisham.ctintegrationsample.R

abstract class AbsSearchAdapter<T>(private val list: List<T>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var itemCallback: ((T) -> Unit)? = null

    private var isFiltering: Boolean = false
    protected var filteredList = mutableListOf<T>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_list_item_view, parent, false)
        return createViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (isFiltering) {
            filteredList.size
        } else {
            list.size
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = if (isFiltering) filteredList[position] else list[position]
        bindViewHolder(holder, item)
    }

    abstract fun createViewHolder(view: View): RecyclerView.ViewHolder

    abstract fun bindViewHolder(holder: RecyclerView.ViewHolder, item: T)

    open fun filter(value: String) {
        isFiltering = true
    }

    fun onItemClickListener(callback: (T) -> Unit) {
        itemCallback = callback
    }
}
