package ru.sberbank.sdakit.demo.config

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import ru.sberbank.sdakit.messenger.integration.domain.sdk.SmartMessengerSDK

@GlideModule
class DemoGlideModule : AppGlideModule() {
    private val messengerGlideModule = SmartMessengerSDK.libraryGlideModule
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        messengerGlideModule.registerComponents(context, glide, registry)
    }
}
