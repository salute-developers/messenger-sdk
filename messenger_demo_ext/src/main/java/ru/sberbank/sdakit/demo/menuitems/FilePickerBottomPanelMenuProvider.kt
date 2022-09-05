package ru.sberbank.sdakit.demo.menuitems

import android.content.Context
import im.dlg.menuitem.custom.domain.info.CustomMenuButtonInfo
import im.dlg.menuitem.custom.domain.info.CustomMenuButtonsInfoWrapper
import im.dlg.menuitem.custom.domain.params.FilePickerCustomParams
import im.dlg.menuitem.custom.domain.providers.CustomMenuProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.sberbank.sdakit.core.di.platform.AppContext
import ru.sberbank.sdakit.demo.R

class FilePickerBottomPanelMenuProvider(
    @AppContext context: Context,
    params: FilePickerCustomParams
) : CustomMenuProvider {

    private val gallery = CustomMenuButtonInfo(
        icon = R.drawable.ic_gallery_24,
        text = context.getString(R.string.bottom_picker_panel_gallery),
        actionCallback = params.openGallery
    )

    private val icon = CustomMenuButtonInfo(
        icon = R.drawable.ic_bottom_panel_file,
        text = context.getString(R.string.bottom_picker_panel_file),
        actionCallback = params.openPickFile
    )

    override val customInfoWrapper: CustomMenuButtonsInfoWrapper
        get() = CustomMenuButtonsInfoWrapper(
            listOf(
                gallery,
                icon,
            )
        )

    override val customInfoWrapperFlow: Flow<CustomMenuButtonsInfoWrapper?>
        get() = flow {
            emit(customInfoWrapper)
        }
}
