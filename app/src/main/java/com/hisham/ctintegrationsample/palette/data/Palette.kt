package com.hisham.ctintegrationsample.palette.data

import android.os.Parcelable
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.hisham.ctintegrationsample.R
import kotlinx.android.parcel.Parcelize

sealed class PaletteUiData {
    data class Item(val paletteDetails: PaletteDetails, val isSelected: Boolean) : PaletteUiData()
    data class Header(@StringRes val title: Int) : PaletteUiData()
}

@Parcelize
data class PaletteDetails(
    @StringRes val name: Int,
    @ColorRes val primary: Int,
    @ColorRes val primaryDark: Int,
    @ColorRes val accent: Int?
) : Parcelable

object PalettesFactory {

    fun palettes(@StringRes currentSelectedName: Int): List<PaletteUiData> {
        val ctPalettes = listOf(
            buildPaletteItem(
                currentSelectedName,
                R.string.carTrawlerGeneric,
                R.color.genericPrimary,
                R.color.genericDarkPrimary,
                R.color.genericAccent
            ),
            buildPaletteItem(
                currentSelectedName,
                R.string.carTrawlerGenericLight,
                R.color.genericLightPrimary,
                R.color.genericLightDarkPrimary,
                R.color.genericLightAccent
            )
        )

        val partnersPalettes = listOf(
            buildPaletteItem(
                currentSelectedName,
                R.string.partnerEasyJet,
                R.color.easyJetPrimary,
                R.color.easyJetDarkPrimary,
                R.color.easyJetAccent
            ),
            buildPaletteItem(
                currentSelectedName,
                R.string.partnereDreams,
                R.color.eDreamsPrimary,
                R.color.eDreamsDarkPrimary,
                R.color.eDreamsAccent
            ),
            buildPaletteItem(
                currentSelectedName,
                R.string.partnerNorwegian,
                R.color.norwegianPrimary,
                R.color.norwegianDarkPrimary,
                R.color.norwegianAccent
            ),
            buildPaletteItem(
                currentSelectedName,
                R.string.partnerItaka,
                R.color.itakaPrimary,
                R.color.itakaDarkPrimary,
                R.color.itakaAccent
            ),
            buildPaletteItem(
                currentSelectedName,
                R.string.partnerGoVoyages,
                R.color.goVoyagesPrimary,
                R.color.goVoyagesDarkPrimary,
                null
            ),
            buildPaletteItem(
                currentSelectedName,
                R.string.partnerOpodo,
                R.color.opodoPrimary,
                R.color.opodoDarkPrimary,
                R.color.opodoAccent
            )
        )

        return arrayListOf<PaletteUiData>().apply {
            add(PaletteUiData.Header(R.string.carTrawler))
            addAll(ctPalettes)
            add(PaletteUiData.Header(R.string.partner))
            addAll(partnersPalettes)
        }
    }

    private fun buildPaletteItem(
        currentSelectedName: Int,
        name: Int,
        primary: Int,
        primaryDark: Int,
        accent: Int?
    ): PaletteUiData.Item {
        return PaletteUiData.Item(
            PaletteDetails(
                name,
                primary,
                primaryDark,
                accent
            ), currentSelectedName == name
        )
    }
}
