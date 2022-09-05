package ru.sberbank.sdakit.demo.menuitems

import android.content.Context
import android.widget.Toast
import im.dlg.menuitem.custom.domain.info.CustomFastActionInfo
import im.dlg.menuitem.custom.domain.info.CustomFastActionInfoWrapper
import im.dlg.menuitem.custom.domain.params.MessageCustomParams
import im.dlg.menuitem.custom.domain.providers.FastActionMenuProvider
import ru.sberbank.sdakit.core.di.platform.AppContext
import ru.sberbank.sdakit.demo.R

class MessageFastActionProvider(
    @AppContext
    private val appContext: Context,
    params: MessageCustomParams
) : FastActionMenuProvider {
    private val customButton: CustomFastActionInfo = CustomFastActionInfo(
        drawableResId = R.drawable.ic_custom_action_24dp,
        textResId = R.string.message_custom_action_title,
        text = appContext.getString(R.string.message_custom_action_title),
        actionCallback = {
            Toast.makeText(
                appContext,
                appContext.getString(R.string.message_custom_action_toast, params.toString()),
                Toast.LENGTH_LONG
            ).show()
        }
    )

    override val customInfoWrapper: CustomFastActionInfoWrapper
        get() = CustomFastActionInfoWrapper(
            customFastActionInfo = listOf(customButton)
        )
}
