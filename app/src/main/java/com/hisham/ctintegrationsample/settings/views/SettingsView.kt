package com.hisham.ctintegrationsample.settings.views

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.view.isVisible
import com.hisham.ctintegrationsample.R
import kotlinx.android.synthetic.main.settings_view.view.*

class SettingsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.settings_view, this)
        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.SettingsView, 0, 0)

        try {
            title(a.getString(R.styleable.SettingsView_settingsTitle) ?: "")
            value(a.getString(R.styleable.SettingsView_settingsValue) ?: "")
            paletteView.isVisible = a.getBoolean(R.styleable.SettingsView_settingsShowPalette, false)
        } finally {
            a.recycle()
        }
    }

    fun title(title: String) {
        titleTv.text = title
    }

    fun value(value: String) {
        valueTv.text = value
    }

    fun palette(primary: Int, primaryDark: Int, accent: Int) {
        paletteView.apply {
            apply(primary, primaryDark, accent)
            isVisible = true
        }
    }
}