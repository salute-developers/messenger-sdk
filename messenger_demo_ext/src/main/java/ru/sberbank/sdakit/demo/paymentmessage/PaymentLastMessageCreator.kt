package ru.sberbank.sdakit.demo.paymentmessage

import android.content.Context
import android.text.SpannableStringBuilder
import im.dlg.platform.message.custom.domain.CustomMessageHandler
import org.json.JSONObject

/**
 * @author Алексей Соколов on 20.07.2022
 */
internal class PaymentLastMessageCreator : CustomMessageHandler.CustomMessageLastMessageCreator {
    override fun createLastMessage(context: Context, message: JSONObject): CharSequence {
        val payload = message.getPayloadData()

        val res = SpannableStringBuilder()
        res.append(payload.getOperation())
        return res
    }
}
