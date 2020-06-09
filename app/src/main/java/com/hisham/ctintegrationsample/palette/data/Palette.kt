package com.hisham.ctintegrationsample.palette.data

import android.os.Parcelable
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.hisham.ctintegrationsample.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PaletteDetails(
    @StringRes val name: Int,
    @ColorRes val primary: Int,
    @ColorRes val primaryDark: Int,
    @ColorRes val accent: Int?
) : Parcelable

data class Palettes(
    val ctPalettes: List<PaletteDetails>,
    val partnersPalettes: List<PaletteDetails>
)

object PalettesFactory {

    fun palettes(): Palettes {
        val ctPalettes = listOf(
            PaletteDetails(
                R.string.carTrawler,
                R.color.genericPrimary,
                R.color.genericDarkPrimary,
                R.color.genericAccent
            ),
            PaletteDetails(
                R.string.carTrawler,
                R.color.genericLightPrimary,
                R.color.genericLightDarkPrimary,
                R.color.genericLightAccent
            )
        )

        val partnersPalettes = listOf(
            PaletteDetails(
                R.string.partnerEasyJet,
                R.color.easyJetPrimary,
                R.color.easyJetDarkPrimary,
                R.color.easyJetAccent
            ),
            PaletteDetails(
                R.string.partnereDreams,
                R.color.eDreamsPrimary,
                R.color.eDreamsDarkPrimary,
                R.color.eDreamsAccent
            ),
            PaletteDetails(
                R.string.partnerNorwegian,
                R.color.norwegianPrimary,
                R.color.norwegianDarkPrimary,
                R.color.norwegianAccent
            ),
            PaletteDetails(
                R.string.partnerItaka,
                R.color.itakaPrimary,
                R.color.itakaDarkPrimary,
                R.color.itakaAccent
            ),
            PaletteDetails(
                R.string.partnerGoVoyages,
                R.color.goVoyagesPrimary,
                R.color.goVoyagesDarkPrimary,
                null
            ),
            PaletteDetails(
                R.string.partnerOpodo,
                R.color.opodoPrimary,
                R.color.opodoDarkPrimary,
                R.color.opodoAccent
            )
        )

        return Palettes(ctPalettes, partnersPalettes)
    }
}
