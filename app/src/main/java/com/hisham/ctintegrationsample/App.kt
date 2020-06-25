package com.hisham.ctintegrationsample

import androidx.multidex.MultiDexApplication
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import com.microsoft.appcenter.distribute.Distribute
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        AppCenter.start(
            this,
            getString(R.string.appCenter_secret),
            Distribute::class.java,
            Analytics::class.java,
            Crashes::class.java
        )
    }
}