package ru.sberbank.sdakit.demo.config

import im.dlg.platform.featureflag.data.FeatureFlag
import im.dlg.platform.featureflag.domain.FeatureFlagSource

class MessengerDemoFeatureFlagSource : FeatureFlagSource {

    override fun getFeatureFlagValue(featureFlag: FeatureFlag): String? {
        return when (featureFlag) {
            FeatureFlag.IsUserProfileEnabled -> true.toString()
            FeatureFlag.IsMuteChatEnabled -> true.toString()
            FeatureFlag.IsAttachMessagesEnabled -> true.toString()
            else -> null
        }
    }
}
