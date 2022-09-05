package ru.sberbank.sdakit.demo.menuitems

import android.content.Context
import android.widget.Toast
import im.dlg.menuitem.custom.domain.info.CustomFastActionInfo
import im.dlg.menuitem.custom.domain.info.CustomFastActionInfoWrapper
import im.dlg.menuitem.custom.domain.params.DialogCustomParams
import im.dlg.menuitem.custom.domain.providers.FastActionMenuProvider
import ru.sberbank.sdakit.core.di.platform.AppContext
import ru.sberbank.sdakit.demo.R

/**
 * @author Алексей Соколов on 03.08.2022
 */
class DialogFastActionProvider(
    @AppContext
    private val appContext: Context,
    params: DialogCustomParams
) : FastActionMenuProvider {

    private val customButton: CustomFastActionInfo = CustomFastActionInfo(
        drawableResId = R.drawable.ic_custom_action_24dp,
        textResId = R.string.dialog_custom_action_title,
        text = appContext.getString(R.string.dialog_custom_action_title),
        actionCallback = {
            Toast.makeText(
                appContext,
                appContext.getString(R.string.dialog_custom_action_toast, params.toString()),
                Toast.LENGTH_LONG
            ).show()
        }
    )

    override val customInfoWrapper: CustomFastActionInfoWrapper
        get() = CustomFastActionInfoWrapper(
            customFastActionInfo = listOf(customButton),
        )
}
