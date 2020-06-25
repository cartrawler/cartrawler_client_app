package com.hisham.ctintegrationsample.palette.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.hisham.ctintegrationsample.R
import com.hisham.ctintegrationsample.palette.data.PaletteUiData
import kotlinx.android.synthetic.main.palette_list_item.view.*

class PaletteItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.palette_list_item, this)
    }

    fun bind(data: PaletteUiData.Item) {
        with(data.paletteDetails) {
            titleTv.text = context.getString(name)
            themePaletteView.apply(primary, primaryDark, accent)
        }

        updateSelection(data)
    }

    private fun updateSelection(data: PaletteUiData.Item) {
        paletteCardView.apply {
            isChecked = data.isSelected
            if (data.isSelected) {
                strokeColor = ContextCompat.getColor(context, R.color.genericPrimary)
                strokeWidth = context.resources.getDimensionPixelSize(R.dimen.palette_border)
            } else {
                strokeWidth = 0
            }
        }
    }
}