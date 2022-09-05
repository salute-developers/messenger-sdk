package ru.sberbank.sdakit.demo.paymentmessage

import im.dlg.platform.message.custom.domain.CustomMessageHandler
import im.dlg.platform.message.custom.domain.CustomMessageHandlers
import org.json.JSONObject
import ru.sberbank.sdakit.demo.paymentmessage.viewholders.IncomingPaymentMessageViewHolder
import ru.sberbank.sdakit.demo.paymentmessage.viewholders.OutgoingPaymentMessageViewHolder

/**
 * @author Алексей Соколов on 20.07.2022
 */
internal class PaymentMessageHandlersImpl(
    router: Router
) : CustomMessageHandlers {
    override val data: Set<CustomMessageHandler> = setOf(object : CustomMessageHandler {
        override fun canHandle(message: JSONObject): Boolean {
            return message.optString("type") == "SBOL_KZ_PAY"
        }

        override val viewHolderIncoming = IncomingPaymentMessageViewHolder(router)

        override val viewHolderOutgoing = OutgoingPaymentMessageViewHolder(router)

        override val creatorLastMessage = PaymentLastMessageCreator()
    })
}
