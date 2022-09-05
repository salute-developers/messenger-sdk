package ru.sberbank.sdakit.demo.paymentmessage.viewholders

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.view.isVisible
import im.dlg.platform.message.custom.domain.CustomMessageHandler
import org.json.JSONObject
import ru.sberbank.sdakit.demo.R
import ru.sberbank.sdakit.demo.paymentmessage.Payload
import ru.sberbank.sdakit.demo.paymentmessage.Router
import ru.sberbank.sdakit.demo.paymentmessage.getAmount
import ru.sberbank.sdakit.demo.paymentmessage.getDeeplink
import ru.sberbank.sdakit.demo.paymentmessage.getOperation
import ru.sberbank.sdakit.demo.paymentmessage.getPayloadData
import ru.sberbank.sdakit.demo.paymentmessage.isPayByBillAction

/**
 * @author Алексей Соколов on 20.07.2022
 */
internal open class BasePaymentMessageViewHolder(
    private val rootId: Int,
    private val router: Router,
) : CustomMessageHandler.CustomMessageViewHolder {
    private lateinit var root: View
    private lateinit var title: TextView
    private lateinit var payButton: TextView
    private lateinit var description: TextView

    override fun create(parent: FrameLayout) {
        root = LayoutInflater.from(parent.context).inflate(rootId, parent)
        title = root.findViewById(R.id.vTitle)
        description = root.findViewById(R.id.vDescription)
        payButton = root.findViewById(R.id.vPayButton)
    }

    override fun bind(parent: FrameLayout, message: JSONObject) {
        val payload = message.getPayloadData()

        title.text = payload.getOperation()
        description.text = payload.getAmount()
        payButton.isVisible = payload.isPayByBillAction()
        setClickListener(parent, payload)
    }

    override fun unbind(parent: FrameLayout) {
        parent.setOnClickListener(null)
    }

    private fun setClickListener(parent: FrameLayout, payload: Payload) {
        val deeplink = payload.getDeeplink()
        if (deeplink != null && router.canHandle(parent.context, Uri.parse(deeplink))) {
            parent.setOnClickListener {
                router.openDeepLink(
                    parent.context,
                    Uri.parse(deeplink)
                )
            }
        } else {
            parent.setOnClickListener(null)
        }
    }

    override fun markHidden(message: JSONObject): Boolean {
        return message.optBoolean("is_edit_mark_hidden")
    }

    override fun statusHidden(message: JSONObject): Boolean {
        return message.optBoolean("is_status_hidden")
    }

    override fun authorHidden(message: JSONObject): Boolean {
        return message.optBoolean("is_author_hidden")
    }
}
