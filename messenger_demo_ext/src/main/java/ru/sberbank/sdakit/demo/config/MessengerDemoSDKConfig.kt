package ru.sberbank.sdakit.demo.config

import android.content.Context
import im.dlg.menuitem.custom.domain.CustomProvidersHolder
import im.dlg.platform.featureflag.domain.FeatureFlagSource
import im.dlg.platform.message.custom.domain.CustomMessageHandlers
import ru.sberbank.sdakit.core.logging.domain.LoggerFactory
import ru.sberbank.sdakit.demo.batterymessage.BatteryMessageHandlersImpl
import ru.sberbank.sdakit.demo.menuitems.CustomProvidersHolderImpl
import ru.sberbank.sdakit.demo.navigation.MessengerDemoNavigators
import ru.sberbank.sdakit.messenger.integration.data.SDKConfig
import ru.sberbank.sdakit.messenger.integration.domain.MessengerEndpoint
import ru.sberbank.sdakit.messenger.integration.domain.MessengerNavigators
import ru.sberbank.sdakit.messenger.integration.domain.auth.MessengerAuthWatcher
import java.util.TimeZone

class MessengerDemoSDKConfig(
    private val appContext: Context,
    private val messengerAuthWatcherImpl: MessengerAuthWatcher,
    private val messengerNavigatorImpl: MessengerNavigators,
    defFeatureFlagSource: FeatureFlagSource,
    messengerDemoFeatureFlagSource: MessengerDemoFeatureFlagSource,
) : SDKConfig {
    override val endpoint: MessengerEndpoint = MessengerDemoEndpointBuilder.build()
    override val appId: Int = 5
    override val timeZone: String = TimeZone.getDefault().id
    override val timeout: Long = 10_000L
    override val logNetworkInRelease: Boolean = true
    override val dialogsEnabled: Boolean = true
    override val loggerFactory: LoggerFactory? = null
    override val featureFlagSource: FeatureFlagSource = messengerDemoFeatureFlagSource
    override val defaultFeatureFlagSource: FeatureFlagSource = defFeatureFlagSource
    // прокинуть сюда Handler
    override val customMessageHandlers: CustomMessageHandlers
        get() = BatteryMessageHandlersImpl()
    override val customProvidersHolder: CustomProvidersHolder
        get() = CustomProvidersHolderImpl(
            appContext,
            MessengerDemoNavigators.dialogListNavigator,
            MessengerDemoNavigators.jazzCommunicationNavigator
        )
    override val messengerAuthWatcher: MessengerAuthWatcher
        get() = messengerAuthWatcherImpl
    override val messengerNavigators: MessengerNavigators
        get() = messengerNavigatorImpl
}
