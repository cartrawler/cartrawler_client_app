package com.hisham.ctintegrationsample.palette.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.hisham.ctintegrationsample.R
import com.hisham.ctintegrationsample.databinding.PaletteListItemBinding
import com.hisham.ctintegrationsample.palette.data.PaletteUiData

class PaletteItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding: PaletteListItemBinding = PaletteListItemBinding.inflate(LayoutInflater.from(context), this, true)

    fun bind(data: PaletteUiData.Item) {
        with(data.paletteDetails) {
            binding.titleTv.text = context.getString(name)
            binding.themePaletteView.apply(primary, primaryDark, accent)
        }

        updateSelection(data)
    }

    private fun updateSelection(data: PaletteUiData.Item) {
        binding.paletteCardView.apply {
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