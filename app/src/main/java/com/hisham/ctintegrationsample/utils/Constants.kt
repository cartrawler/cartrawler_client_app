package com.hisham.ctintegrationsample.utils

import cartrawler.core.base.USPDisplayType

object Constants {

    fun uspTypes() = HashMap<String, USPDisplayType>().apply {
        put(USPDisplayType.DEFAULT_STYLE.name, USPDisplayType.DEFAULT_STYLE)
        put(USPDisplayType.CHECK_STYLE.name, USPDisplayType.CHECK_STYLE)
    }
}