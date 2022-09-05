package ru.sberbank.sdakit.demo.batterymessage

import im.dlg.platform.message.custom.domain.CustomMessageHandler
import im.dlg.platform.message.custom.domain.CustomMessageHandlers
import org.json.JSONObject
import ru.sberbank.sdakit.demo.batterymessage.viewholders.IncomingBatteryViewHolder
import ru.sberbank.sdakit.demo.batterymessage.viewholders.OutgoingBatteryViewHolder

internal class BatteryMessageHandlersImpl : CustomMessageHandlers {
    override val data: Set<CustomMessageHandler>
        get() = setOf(object : CustomMessageHandler {
            override fun canHandle(message: JSONObject): Boolean {
                return message.optString("type") == "BATTERY"
            }

            override val viewHolderIncoming: CustomMessageHandler.CustomMessageViewHolder
                get() = IncomingBatteryViewHolder()
            override val viewHolderOutgoing: CustomMessageHandler.CustomMessageViewHolder
                get() = OutgoingBatteryViewHolder()
            override val creatorLastMessage: CustomMessageHandler.CustomMessageLastMessageCreator
                get() = BatteryLastMessageCreator()
        })
}
