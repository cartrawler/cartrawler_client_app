package com.hisham.ctintegrationsample.palette.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.StringRes
import com.hisham.ctintegrationsample.R
import kotlinx.android.synthetic.main.palette_list_header_item.view.*

class PaletteHeaderItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.palette_list_header_item, this)
    }

    fun bind(@StringRes title: Int) {
        paletteHeaderTv.text = context.getString(title)
    }
}