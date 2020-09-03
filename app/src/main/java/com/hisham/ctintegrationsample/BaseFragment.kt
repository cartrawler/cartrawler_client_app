package com.hisham.ctintegrationsample

import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    fun toolbarIcon(@DrawableRes drawableResId: Int) {
        requireActivity().actionBar?.setLogo(drawableResId)
    }
}