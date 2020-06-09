package com.hisham.ctintegrationsample.palette

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.hisham.ctintegrationsample.BaseFragment
import com.hisham.ctintegrationsample.R
import com.hisham.ctintegrationsample.palette.data.PalettesFactory
import com.hisham.ctintegrationsample.palette.views.PaletteHeaderItemView
import com.hisham.ctintegrationsample.palette.views.PaletteItemView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.palettes_fragment.*

class PalettesFragment : BaseFragment() {

    private val groupAdapter: GroupAdapter<GroupieViewHolder> = GroupAdapter<GroupieViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.palettes_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        groupAdapter.spanCount = 2
        recyclerView.apply {
            adapter = groupAdapter
            layoutManager = GridLayoutManager(requireContext(), groupAdapter.spanCount).apply {
                spanSizeLookup = groupAdapter.spanSizeLookup
            }
        }

        populateAdapter()
    }

    private fun populateAdapter() {
        val (ctPalettes, partnersPalettes) = PalettesFactory.palettes()

        // CT Palettes
        groupAdapter.add(Section().apply {
            setHeader(PaletteHeaderItemView(getString(R.string.carTrawler)))
            ctPalettes.forEach { add(PaletteItemView(it)) }
        })

        // Partner Palettes
        groupAdapter.add(Section().apply {
            setHeader(PaletteHeaderItemView(getString(R.string.partner)))
            partnersPalettes.forEach { add(PaletteItemView(it)) }
        })
    }
}
