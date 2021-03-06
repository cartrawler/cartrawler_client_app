package com.hisham.ctintegrationsample.settings.views

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.hisham.ctintegrationsample.R
import com.hisham.ctintegrationsample.databinding.ThemePaletteViewBinding

class ThemePaletteView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding = ThemePaletteViewBinding.inflate(LayoutInflater.from(context), this, true)

    fun apply(primary: Int, primaryDark: Int, accent: Int?) = with(binding) {
        tintImage(primaryColourIndicatorView, primary)
        tintImage(primaryDarkColourIndicatorView, primaryDark)

        if (accent != null) {
            tintImage(accentColourIndicatorView, accent)
            accentColourIndicatorView.isVisible = true
        } else {
            tintImage(accentColourIndicatorView, R.color.ct_white)
            accentColourIndicatorView.isInvisible = true
        }
    }

    private fun tintImage(imageView: ImageView, color: Int) {
        imageView.setImageDrawable(buildCircle(color))
    }

    private fun buildCircle(fillColor: Int): Drawable {
        val drawable = GradientDrawable()
        val resolvedColor = ContextCompat.getColor(context, fillColor)
        val isWhite = resolvedColor == Color.WHITE

        drawable.apply {
            color = ColorStateList.valueOf(resolvedColor)
            shape = GradientDrawable.OVAL
            if (isWhite) {
                setStroke(2, Color.parseColor("#c7c7cd"))
            } else {
                setStroke(0, 0)
            }
        }

        return drawable
    }
}