package com.hisham.ctintegrationsample.landingSelector

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import by.kirich1409.viewbindingdelegate.viewBinding
import com.hisham.ctintegrationsample.R
import com.hisham.ctintegrationsample.core.LocalStorage
import com.hisham.ctintegrationsample.databinding.SelectorViewBinding
import com.hisham.ctintegrationsample.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LandingSelectorFragment : Fragment(R.layout.selector_view) {

    @Inject
    lateinit var localStorage: LocalStorage

    val binding by viewBinding(SelectorViewBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val selectorAdapter = SelectorAdapter {
            localStorage.landingStyle = it
            findNavController().popBackStack()
        }

        val selectorRecyclerView = binding.selectorRecyclerView
        selectorRecyclerView.addItemDecoration(
            DividerItemDecoration(context, LinearLayout.VERTICAL)
        )
        selectorRecyclerView.adapter = selectorAdapter

        selectorAdapter.submitList(
            Constants.uspTypes().values.toList()
        )
    }
}