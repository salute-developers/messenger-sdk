package ru.sberbank.sdakit.demo.paymentmessage

import org.json.JSONObject
import ru.sberbank.sdakit.core.utils.json.optStringNullable

/**
 * @author Алексей Соколов on 20.07.2022
 */
internal fun JSONObject.getPayloadData(): Payload {
    val res = optJSONObject("payload") ?: JSONObject()
    return Payload(res)
}

internal fun Payload.getOperation(): String = base.optString("operation")

internal fun Payload.getAmount(): String = base.optString("amount")

internal fun Payload.getDeeplink(): String? = base.optStringNullable("internal_link")

internal fun Payload.isPayByBillAction(): Boolean = base.optStringNullable("action") == "paymentByBill"

@JvmInline
internal value class Payload(val base: JSONObject)
