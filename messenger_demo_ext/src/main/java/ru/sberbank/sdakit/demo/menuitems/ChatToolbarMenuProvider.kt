package ru.sberbank.sdakit.demo.menuitems

import android.content.Context
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import im.dlg.menuitem.custom.domain.info.CollapsedButtonInfo
import im.dlg.menuitem.custom.domain.info.CustomButtonInfo
import im.dlg.menuitem.custom.domain.info.CustomButtonsInfoWrapper
import im.dlg.menuitem.custom.domain.params.ToolbarChatCustomParams
import im.dlg.menuitem.custom.domain.providers.ToolbarMenuProvider
import im.dlg.platform.navigation.presentation.JazzCommunicationNavigator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import ru.sberbank.sdakit.core.di.platform.AppContext
import ru.sberbank.sdakit.demo.R

/**
 * @author Алексей Соколов on 08.07.2022
 */
class ChatToolbarMenuProvider @AssistedInject constructor(
    private val jazzCommunicationNavigator: JazzCommunicationNavigator,
    @Assisted toolbarChatCustomParams: ToolbarChatCustomParams,
    @AppContext context: Context
) : ToolbarMenuProvider {

    private val camera: CustomButtonInfo = CustomButtonInfo(
        mainDrawable = R.drawable.ic_icon_video_24,
        text = context.getString(R.string.chat_video_call)
    )

    override val customInfoWrapper: CustomButtonsInfoWrapper
        get() = CustomButtonsInfoWrapper(
            customButtonsInfo = listOfNotNull(
                camera,
            ),
            collapsedButtonInfo = CollapsedButtonInfo(R.drawable.toolbar_item_tree_dots)
        )

    override val customInfoWrapperFlow: Flow<CustomButtonsInfoWrapper> =
        toolbarChatCustomParams
            .isVideoCallsEnabled
            .combine(toolbarChatCustomParams.recipientName) { isVideoCallsEnabled, recipientName ->
                Pair(isVideoCallsEnabled, recipientName)
            }.map { (isVideoCallsEnabled, recipientName) ->
                val customButtonInfo = mutableListOf<CustomButtonInfo>()

                if (isVideoCallsEnabled) {
                    customButtonInfo += camera.copy {
                        toolbarChatCustomParams.cancelAudioIfRecording()
                        jazzCommunicationNavigator.makeContactVideoCall(
                            toolbarChatCustomParams.peerId,
                            recipientName
                        )
                    }
                }

                CustomButtonsInfoWrapper(
                    customButtonsInfo = customButtonInfo,
                    collapsedButtonInfo = CollapsedButtonInfo(R.drawable.toolbar_item_tree_dots)
                )
            }
}
