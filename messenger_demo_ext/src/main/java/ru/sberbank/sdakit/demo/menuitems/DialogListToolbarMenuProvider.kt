package ru.sberbank.sdakit.demo.menuitems

import android.content.Context
import android.os.Bundle
import im.dlg.menuitem.custom.domain.info.CollapsedButtonInfo
import im.dlg.menuitem.custom.domain.info.CustomButtonInfo
import im.dlg.menuitem.custom.domain.info.CustomButtonsInfoWrapper
import im.dlg.menuitem.custom.domain.params.ToolbarDialogListCustomParams
import im.dlg.menuitem.custom.domain.providers.ToolbarMenuProvider
import im.dlg.platform.navigation.domain.ContactsHostParams
import im.dlg.platform.navigation.presentation.DialogListNavigator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.sberbank.sdakit.demo.R

/**
 * @author Алексей Соколов on 08.07.2022
 */
class DialogListToolbarMenuProvider constructor(
    private val dialogListNavigator: DialogListNavigator,
    toolbarDialogListCustomParams: ToolbarDialogListCustomParams,
    context: Context
) : ToolbarMenuProvider {
    private val itemNewChat: CustomButtonInfo = CustomButtonInfo(
        mainDrawable = R.drawable.toolbar_item_new_chat,
        subDrawable = R.drawable.ic_markers,
        text = context.getString(R.string.dialog_new_chat)
    ) {
        dialogListNavigator.openContactsHost(
            ContactsHostParams(
                navigationState = Bundle()
            )
        )
    }

    override val customInfoWrapper: CustomButtonsInfoWrapper
        get() = CustomButtonsInfoWrapper(
            customButtonsInfo = listOfNotNull(
                itemNewChat,
            ),
            collapsedButtonInfo = CollapsedButtonInfo(R.drawable.toolbar_item_tree_dots)
        )

    override val customInfoWrapperFlow: Flow<CustomButtonsInfoWrapper> =
        toolbarDialogListCustomParams.isContactsPermissionGranted.map { isPermissionGranted ->
            val itemNewChatSubDrawable = if (isPermissionGranted) null else R.drawable.ic_markers
            CustomButtonsInfoWrapper(
                customButtonsInfo = listOfNotNull(
                    itemNewChat.copy(subDrawable = itemNewChatSubDrawable),
                ),
                collapsedButtonInfo = CollapsedButtonInfo(R.drawable.toolbar_item_tree_dots)
            )
        }
}
