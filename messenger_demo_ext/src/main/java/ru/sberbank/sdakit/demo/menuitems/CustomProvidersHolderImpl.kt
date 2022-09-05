package ru.sberbank.sdakit.demo.menuitems

import android.content.Context
import im.dlg.menuitem.custom.domain.CustomProvidersHolder
import im.dlg.menuitem.custom.domain.params.DialogCustomParams
import im.dlg.menuitem.custom.domain.params.FilePickerCustomParams
import im.dlg.menuitem.custom.domain.params.MessageCustomParams
import im.dlg.menuitem.custom.domain.params.ToolbarChatCustomParams
import im.dlg.menuitem.custom.domain.params.ToolbarDialogListCustomParams
import im.dlg.menuitem.custom.domain.params.ToolbarGroupProfileCustomParams
import im.dlg.menuitem.custom.domain.params.ToolbarUserProfileCustomParams
import im.dlg.menuitem.custom.domain.providers.CustomMenuProvider
import im.dlg.menuitem.custom.domain.providers.FastActionMenuProvider
import im.dlg.menuitem.custom.domain.providers.ToolbarMenuProvider
import im.dlg.platform.navigation.presentation.DialogListNavigator
import im.dlg.platform.navigation.presentation.JazzCommunicationNavigator

/**
 * @author Алексей Соколов on 08.07.2022
 */
internal class CustomProvidersHolderImpl(
    private val appContext: Context,
    private val dialogListNavigator: DialogListNavigator,
    private val jazzCommunicationNavigator: JazzCommunicationNavigator,
) : CustomProvidersHolder {

    override fun chatCustomProvider(
        toolbarChatCustomParams: ToolbarChatCustomParams
    ): ToolbarMenuProvider {
        return ChatToolbarMenuProvider(
            jazzCommunicationNavigator,
            toolbarChatCustomParams,
            appContext
        )
    }

    override fun dialogFastActionsCustomProvider(
        dialogCustomParams: DialogCustomParams
    ): FastActionMenuProvider {
        return DialogFastActionProvider(
            appContext,
            dialogCustomParams
        )
    }

    override fun dialogListCustomProvider(
        toolbarDialogListCustomParams: ToolbarDialogListCustomParams
    ): ToolbarMenuProvider {
        return DialogListToolbarMenuProvider(
            dialogListNavigator,
            toolbarDialogListCustomParams,
            appContext
        )
    }

    override fun groupProfileCustomProvider(
        params: ToolbarGroupProfileCustomParams
    ): ToolbarMenuProvider {
        return GroupProfileToolbarMenuProvider(
            appContext,
            params
        )
    }

    override fun messageFastActionCustomProvider(
        params: MessageCustomParams
    ): FastActionMenuProvider {
        return MessageFastActionProvider(appContext, params)
    }

    override fun filePickerBottomPanelMenuProvider(params: FilePickerCustomParams): CustomMenuProvider {
        return FilePickerBottomPanelMenuProvider(appContext, params)
    }
    override fun userProfileCustomProvider(
        params: ToolbarUserProfileCustomParams
    ): ToolbarMenuProvider {
        return UserProfileToolbarMenuProvider(
            appContext,
            params
        )
    }
}
