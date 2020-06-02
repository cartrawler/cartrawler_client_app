package com.hisham.ctintegrationsample.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.hisham.ctintegrationsample.BaseFragment
import com.hisham.ctintegrationsample.R
import kotlinx.android.synthetic.main.settings_fragment.*

class SettingsFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.settings_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarIcon(0)

        val primary = ContextCompat.getColor(requireContext(), R.color.genericPrimary)
        val dark = ContextCompat.getColor(requireContext(), R.color.genericDarkPrimary)
        val accent = ContextCompat.getColor(requireContext(), R.color.genericAccent)
        themeView.palette(primary, dark, accent)
    }
}