package com.hisham.ctintegrationsample.palette

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.hisham.ctintegrationsample.BaseFragment
import com.hisham.ctintegrationsample.R
import com.hisham.ctintegrationsample.core.LocalStorage
import com.hisham.ctintegrationsample.palette.data.PalettesFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.palettes_fragment.*
import javax.inject.Inject

@AndroidEntryPoint
class PalettesFragment : BaseFragment() {

    @Inject
    lateinit var localStorage: LocalStorage

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.palettes_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 2).apply {
                spanSizeLookup = object: GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return if (adapter?.getItemViewType(position) == PalettesAdapter.TYPE_ITEM) 1 else 2
                    }
                }
            }
        }

        populateAdapter()
    }

    private fun populateAdapter() {
        var selectedPaletteName = localStorage.palette.name
        val list = PalettesFactory.palettes(selectedPaletteName)
        val adapter = PalettesAdapter(ArrayList(list))
        recyclerView.adapter = adapter

        adapter.apply {
            onItemClickListener { position, data ->
                selectedPaletteName = data.paletteDetails.name
                adapter.update(position, data.copy(isSelected = true))
                localStorage.palette = data.paletteDetails
            }
        }
    }
}
