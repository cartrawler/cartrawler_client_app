package com.hisham.ctintegrationsample.settings.views

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import com.hisham.ctintegrationsample.R
import kotlinx.android.synthetic.main.theme_palette_view.view.*

class ThemePaletteView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.theme_palette_view, this)
    }

    fun apply(primary: Int, primaryDark: Int, accent: Int) {
        ImageViewCompat.setImageTintList(primaryColourIndicatorView, ColorStateList.valueOf(primary))
        ImageViewCompat.setImageTintList(primaryDarkColourIndicatorView, ColorStateList.valueOf(primaryDark))
        ImageViewCompat.setImageTintList(accentColourIndicatorView, ColorStateList.valueOf(accent))
    }
}