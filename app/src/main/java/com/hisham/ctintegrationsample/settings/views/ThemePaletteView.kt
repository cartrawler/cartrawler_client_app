package com.hisham.ctintegrationsample.settings.views

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
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

    fun apply(primary: Int, primaryDark: Int, accent: Int?) {
        tintImage(primaryColourIndicatorView, primary)
        tintImage(primaryDarkColourIndicatorView, primaryDark)

        if (accent != null) {
            tintImage(accentColourIndicatorView, accent)
        } else {
            accentColourIndicatorView.isInvisible = true
        }
    }

    private fun tintImage(imageView: ImageView, color: Int) {
        val resolvedColor = ContextCompat.getColor(context, color)

        if (resolvedColor != Color.WHITE) {
            ImageViewCompat.setImageTintList(imageView, ColorStateList.valueOf(resolvedColor))
        } else {
            imageView.setImageResource(R.drawable.grey_border_oval_shape)
        }
    }
}