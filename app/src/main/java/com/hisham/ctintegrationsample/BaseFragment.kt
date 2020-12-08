package com.hisham.ctintegrationsample

import androidx.annotation.DrawableRes
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    fun toolbarIcon(@DrawableRes drawableResId: Int) {
        requireActivity().findViewById<Toolbar>(R.id.toolbar).logo = if (drawableResId != 0) {
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_app).run {
                this?.setTint(ContextCompat.getColor(requireContext(), R.color.ct_black))
                this
            }
        } else {
            null
        }
    }
}