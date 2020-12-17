package com.hisham.ctintegrationsample.palette.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.StringRes
import com.hisham.ctintegrationsample.databinding.PaletteListHeaderItemBinding

class PaletteHeaderItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding: PaletteListHeaderItemBinding =
        PaletteListHeaderItemBinding.inflate(LayoutInflater.from(context), this, true)

    fun bind(@StringRes title: Int) {
        binding.paletteHeaderTv.text = context.getString(title)
    }
}