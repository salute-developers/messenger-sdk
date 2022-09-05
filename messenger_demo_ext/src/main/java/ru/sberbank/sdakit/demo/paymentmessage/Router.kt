package ru.sberbank.sdakit.demo.paymentmessage

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.annotation.MainThread

/**
 * Роутер - обработчик входящих диплинков
 * @author Алексей Соколов on 20.07.2022
 */
internal interface Router {
    /**
     * Возвращает true, если диплинк может быть обработан.
     */
    fun canHandle(context: Context, uri: Uri): Boolean

    /**
     * Асинхронная обработка диплинка (в том числе динамического линка Firebase)
     */
    @MainThread
    fun openDeepLink(context: Context, uri: Uri)
}

/**
 * RouterImpl - наивная реализация router-а deeplink-ов
 * @author Алексей Соколов on 20.07.2022
 */
internal class RouterImpl : Router {
    override fun canHandle(context: Context, uri: Uri): Boolean {
        return uri.scheme == SCHEME
    }

    override fun openDeepLink(context: Context, uri: Uri) {
        Toast.makeText(context, "$PATTERN_STR $uri", Toast.LENGTH_LONG).show()
    }

    private companion object {
        const val SCHEME = "messenger_demo"
        const val PATTERN_STR = "Диплинка для обработки"
    }
}
