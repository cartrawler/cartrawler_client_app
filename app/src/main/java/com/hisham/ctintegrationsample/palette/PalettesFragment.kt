package com.hisham.ctintegrationsample.palette

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.hisham.ctintegrationsample.BaseFragment
import com.hisham.ctintegrationsample.core.LocalStorage
import com.hisham.ctintegrationsample.databinding.PalettesFragmentBinding
import com.hisham.ctintegrationsample.palette.data.PalettesFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PalettesFragment : BaseFragment() {

    @Inject
    lateinit var localStorage: LocalStorage

    private lateinit var binding: PalettesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PalettesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.apply {
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
        val list = PalettesFactory.palettes(localStorage.palette.name)
        binding.recyclerView.adapter = PalettesAdapter(ArrayList(list)).apply {
            onItemClickListener { position, data ->
                update(position, data.copy(isSelected = true))
                localStorage.palette = data.paletteDetails
            }
        }
    }
}
