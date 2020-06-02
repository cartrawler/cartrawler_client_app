package com.hisham.ctintegrationsample.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hisham.ctintegrationsample.CarTrawlerInjector
import com.hisham.ctintegrationsample.R
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startStandaloneBtn.setOnClickListener {
            CarTrawlerInjector.initStandalone(requireActivity())
        }

        inPathBtn.setOnClickListener {
            CarTrawlerInjector.initInPath(requireActivity())
        }
    }
}