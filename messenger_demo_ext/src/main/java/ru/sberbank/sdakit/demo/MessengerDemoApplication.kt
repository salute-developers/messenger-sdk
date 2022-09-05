package ru.sberbank.sdakit.demo

import android.app.Application
import android.content.Intent
import android.content.IntentFilter
import androidx.work.Configuration
import androidx.work.DelegatingWorkerFactory
import ru.sberbank.sdakit.demo.auth.MessengerDemoAuthWatcher
import ru.sberbank.sdakit.demo.config.MessengerDemoFeatureFlagSource
import ru.sberbank.sdakit.demo.config.MessengerDemoSDKConfig
import ru.sberbank.sdakit.demo.navigation.MessengerDemoNavigators
import ru.sberbank.sdakit.messenger.integration.domain.sdk.SmartMessengerSDK
import ru.sberbank.sdakit.messenger.sdk.di.ApiProvidersComponent

class MessengerDemoApplication : Application(), Configuration.Provider {

    override fun onCreate() {
        super.onCreate()
        // инициализация Dagger-а
        ApiProvidersComponent.install(this)

        registerReceiver(BatteryReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))

        // инициализация MessengerSdk-а + koin graph-а
        // todo все зависимости в MessengerDemoSDKConfig?
        SmartMessengerSDK.init(
            this@MessengerDemoApplication,
            MessengerDemoSDKConfig(
                this@MessengerDemoApplication,
                MessengerDemoAuthWatcher,
                MessengerDemoNavigators,
                MessengerDemoFeatureFlagSource(),
                MessengerDemoFeatureFlagSource(),
            )
        )
    }

    override fun getWorkManagerConfiguration(): Configuration {
        val factory = DelegatingWorkerFactory().apply {
            addFactory(SmartMessengerSDK.integrationApi.workerFactory)
        }
        return Configuration.Builder()
            .setWorkerFactory(factory)
            .build()
    }
}
