package ru.sberbank.sdakit.demo.batterymessage

import android.content.Context
import android.text.SpannableStringBuilder
import im.dlg.platform.message.custom.domain.CustomMessageHandler
import org.json.JSONObject
import ru.sberbank.sdakit.demo.paymentmessage.getOperation
import ru.sberbank.sdakit.demo.paymentmessage.getPayloadData

class BatteryLastMessageCreator : CustomMessageHandler.CustomMessageLastMessageCreator {
    override fun createLastMessage(context: Context, message: JSONObject): CharSequence {
        val payload = message.getPayloadData()

        val res = SpannableStringBuilder()
        res.append(payload.getOperation())
        return res
    }
}
