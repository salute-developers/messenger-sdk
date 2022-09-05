package ru.sberbank.sdakit.demo.menuitems

import android.content.Context
import android.widget.Toast
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import im.dlg.menuitem.custom.domain.info.CollapsedButtonInfo
import im.dlg.menuitem.custom.domain.info.CustomButtonInfo
import im.dlg.menuitem.custom.domain.info.CustomButtonsInfoWrapper
import im.dlg.menuitem.custom.domain.params.ToolbarUserProfileCustomParams
import im.dlg.menuitem.custom.domain.providers.ToolbarMenuProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.sberbank.sdakit.core.di.platform.AppContext
import ru.sberbank.sdakit.demo.R

/**
 * @author Алексей Соколов on 24.08.2022
 */
class UserProfileToolbarMenuProvider @AssistedInject constructor(
    @AppContext appContext: Context,
    @Assisted params: ToolbarUserProfileCustomParams,
) : ToolbarMenuProvider {
    private val customButtonInfo: CustomButtonInfo = CustomButtonInfo(
        mainDrawable = R.drawable.ic_custom_action_24dp,
        text = appContext.getString(R.string.user_profile_custom_action)
    ) {
        Toast.makeText(
            appContext,
            appContext.getString(R.string.user_profile_custom_toast, params.toString()),
            Toast.LENGTH_LONG
        ).show()
    }

    private val collapsedButtonInfo = CollapsedButtonInfo(
        R.drawable.ic_tree_dots,
        appContext.getString(R.string.more)
    )

    override val customInfoWrapper: CustomButtonsInfoWrapper
        get() = CustomButtonsInfoWrapper(
            customButtonsInfo = listOfNotNull(customButtonInfo),
            collapsedButtonInfo = collapsedButtonInfo
        )

    override val customInfoWrapperFlow: Flow<CustomButtonsInfoWrapper> = flow { emit(customInfoWrapper) }
}
