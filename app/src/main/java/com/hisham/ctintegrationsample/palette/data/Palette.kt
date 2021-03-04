package com.hisham.ctintegrationsample.palette.data

import android.os.Parcelable
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.hisham.ctintegrationsample.R
import kotlinx.parcelize.Parcelize

sealed class PaletteUiData {
    data class Item(val paletteDetails: PaletteDetails, val isSelected: Boolean) : PaletteUiData()
    data class Header(@StringRes val title: Int) : PaletteUiData()
}

@Parcelize
data class PaletteDetails(
    @StringRes val name: Int,
    @StringRes val clientId: Int,
    @ColorRes val primary: Int,
    @ColorRes val primaryDark: Int,
    @ColorRes val accent: Int?
) : Parcelable

object PalettesFactory {

    fun palettes(@StringRes currentSelectedName: Int): List<PaletteUiData> {
        val ctPalettes = listOf(
            buildPaletteItem(
                currentSelectedName,
                name = R.string.carTrawlerGeneric,
                clientId = R.string.carTrawlerGenericClientId,
                primary = R.color.genericPrimary,
                primaryDark = R.color.genericDarkPrimary,
                accent = R.color.genericAccent
            ),
            buildPaletteItem(
                currentSelectedName,
                name = R.string.carTrawlerGenericLight,
                clientId = R.string.carTrawlerGenericLightClientId,
                primary = R.color.genericLightPrimary,
                primaryDark = R.color.genericLightDarkPrimary,
                accent = R.color.genericLightAccent
            )
        )

        val partnersPalettes = listOf(
            buildPaletteItem(
                currentSelectedName,
                name = R.string.partnerEasyJet,
                clientId = R.string.partnerEasyJetClientId,
                primary = R.color.easyJetPrimary,
                primaryDark = R.color.easyJetDarkPrimary,
                accent = R.color.easyJetAccent
            ),
            buildPaletteItem(
                currentSelectedName,
                name = R.string.partnereDreams,
                clientId = R.string.partnereDreamsClientId,
                primary = R.color.eDreamsPrimary,
                primaryDark = R.color.eDreamsDarkPrimary,
                accent = R.color.eDreamsAccent
            ),
            buildPaletteItem(
                currentSelectedName,
                name = R.string.partnerNorwegian,
                clientId = R.string.partnerNorwegianClientId,
                primary = R.color.norwegianPrimary,
                primaryDark = R.color.norwegianDarkPrimary,
                accent = R.color.norwegianAccent
            ),
            buildPaletteItem(
                currentSelectedName,
                name = R.string.partnerItaka,
                clientId = R.string.partnerItakaClientId,
                primary = R.color.itakaPrimary,
                primaryDark = R.color.itakaDarkPrimary,
                accent = R.color.itakaAccent
            ),
            buildPaletteItem(
                currentSelectedName,
                name = R.string.partnerGoVoyages,
                clientId = R.string.partnerGoVoyagesClientId,
                primary = R.color.goVoyagesPrimary,
                primaryDark = R.color.goVoyagesDarkPrimary,
                accent = R.color.goVoyagesAccent
            ),
            buildPaletteItem(
                currentSelectedName,
                name = R.string.partnerOpodo,
                clientId = R.string.partnerOpodoClientId,
                primary = R.color.opodoPrimary,
                primaryDark = R.color.opodoDarkPrimary,
                accent = R.color.opodoAccent
            ),
            buildPaletteItem(
                currentSelectedName,
                name = R.string.partnerTravelLink,
                clientId = R.string.partnerTravelLinkClientId,
                primary = R.color.travellinkPrimary,
                primaryDark = R.color.travellinkDarkPrimary,
                accent = R.color.travellinkAccent
            ),
            buildPaletteItem(
                currentSelectedName,
                name = R.string.partnerTravelStart,
                clientId = R.string.partnerTravelStartClientId,
                primary = R.color.travelStartPrimary,
                primaryDark = R.color.travelStartDarkPrimary,
                accent = R.color.travelStartAccent
            ),
            buildPaletteItem(
                currentSelectedName,
                name = R.string.partnerFinno,
                clientId = R.string.partnerFinnoClientId,
                primary = R.color.finnoPrimary,
                primaryDark = R.color.finnoDarkPrimary,
                accent = R.color.finnoAccent
            ),
            buildPaletteItem(
                currentSelectedName,
                name = R.string.partnerTAP,
                clientId = R.string.partnerTAPClientId,
                primary = R.color.TAPPrimary,
                primaryDark = R.color.TAPDarkPrimary,
                accent = R.color.TAPAccent
            ),
            buildPaletteItem(
                currentSelectedName,
                name = R.string.partnerRental24h,
                clientId = R.string.partnerRental24hClientId,
                primary = R.color.rental24hPrimary,
                primaryDark = R.color.rental24hDarkPrimary,
                accent = R.color.rental24hAccent
            ),
            buildPaletteItem(
                currentSelectedName,
                name = R.string.partnerHolidayAutos,
                clientId = R.string.partnerHolidayAutosClientId,
                primary = R.color.holidayAutosPrimary,
                primaryDark = R.color.holidayAutosDarkPrimary,
                accent = R.color.holidayAutosAccent
            ),
            buildPaletteItem(
                currentSelectedName,
                name = R.string.partnerArgus,
                clientId = R.string.partnerArgusClientId,
                primary = R.color.argusPrimary,
                primaryDark = R.color.argusDarkPrimary,
                accent = R.color.argusAccent
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
        clientId: Int,
        primary: Int,
        primaryDark: Int,
        accent: Int?
    ): PaletteUiData.Item {
        return PaletteUiData.Item(
            PaletteDetails(
                name,
                clientId,
                primary,
                primaryDark,
                accent
            ), currentSelectedName == name
        )
    }
}
