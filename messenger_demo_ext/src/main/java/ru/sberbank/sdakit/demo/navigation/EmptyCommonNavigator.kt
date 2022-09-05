package ru.sberbank.sdakit.demo.navigation

import android.content.Context
import android.content.Intent
import android.widget.Toast
import im.dlg.platform.external.Peer
import im.dlg.platform.navigation.domain.ChatParams
import im.dlg.platform.navigation.domain.ChatsForForwardingParams
import im.dlg.platform.navigation.domain.ContactsHostParams
import im.dlg.platform.navigation.domain.ExitParams
import im.dlg.platform.navigation.domain.FullScreenParams
import im.dlg.platform.navigation.domain.PeerResolverParams
import im.dlg.platform.navigation.domain.ProfileGroupParams
import im.dlg.platform.navigation.domain.SearchHostParams
import im.dlg.platform.navigation.domain.UserProfileParams
import im.dlg.platform.navigation.presentation.ChatNavigator
import im.dlg.platform.navigation.presentation.ChatsForForwardingNavigator
import im.dlg.platform.navigation.presentation.ContactsHostNavigator
import im.dlg.platform.navigation.presentation.ContactsNavigator
import im.dlg.platform.navigation.presentation.DeepLinkNavigator
import im.dlg.platform.navigation.presentation.DialogListNavigator
import im.dlg.platform.navigation.presentation.GroupProfileNavigator
import im.dlg.platform.navigation.presentation.JazzCommunicationNavigator
import im.dlg.platform.navigation.presentation.PeerResolverNavigator
import im.dlg.platform.navigation.presentation.SearchProfileHostNavigator
import im.dlg.platform.navigation.presentation.SimpleNavigator
import im.dlg.platform.navigation.presentation.UserProfileNavigator

internal class EmptyCommonNavigator constructor(private val reqContext: () -> Context?) :
    ChatNavigator,
    ChatsForForwardingNavigator,
    ContactsHostNavigator,
    ContactsNavigator,
    DeepLinkNavigator,
    DialogListNavigator,
    GroupProfileNavigator,
    JazzCommunicationNavigator,
    PeerResolverNavigator,
    SearchProfileHostNavigator,
    UserProfileNavigator,
    SimpleNavigator {

    override fun openChat(paramsIn: ChatParams) = notImplemented(reqContext())

    override fun openProfileGroup(paramsIn: ProfileGroupParams) = notImplemented(reqContext())

    override fun openContactsToForward(paramsIn: ChatsForForwardingParams) = notImplemented(reqContext())

    override fun openUserProfile(paramsIn: UserProfileParams) = notImplemented(reqContext())

    override fun getFullScreenIntent(paramsIn: FullScreenParams) = Intent()

    override fun openProfile(paramsIn: UserProfileParams) = notImplemented(reqContext())

    override fun exit(exitParams: ExitParams) = notImplemented(reqContext())

    override fun openCreatedGroup(paramsIn: ChatParams) = notImplemented(reqContext())

    override fun openUserDevicesScreen() = notImplemented(reqContext())

    override fun openCreateConferenceScreen() = notImplemented(reqContext())

    override fun openInviteContact(name: String, phone: String) = notImplemented(reqContext())

    override fun openInviteGeneral() = notImplemented(reqContext())

    override fun openPeerResolver(paramsIn: PeerResolverParams) = notImplemented(reqContext())

    override fun openContactsHost(paramsIn: ContactsHostParams) = notImplemented(reqContext())

    override fun openSearchHost(paramsIn: SearchHostParams) = notImplemented(reqContext())

    override fun makeContactVideoCall(peerId: String, recipientName: String) = notImplemented(reqContext())

    override fun openNextScreen(peer: Peer, paramsIn: PeerResolverParams) = notImplemented(reqContext())

    override fun exit() = notImplemented(reqContext())

    private companion object {
        fun notImplemented(context: Context?) {
            context?.let { ctx ->
                Toast.makeText(ctx, "Эта часть навигации не реализована", Toast.LENGTH_SHORT)
                    .show()
            } ?: throw IllegalStateException("Context не проинициализирован")
        }
    }
}
