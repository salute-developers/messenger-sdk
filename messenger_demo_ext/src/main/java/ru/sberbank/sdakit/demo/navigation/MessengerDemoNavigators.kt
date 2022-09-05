package ru.sberbank.sdakit.demo.navigation

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import im.dlg.platform.navigation.domain.ChatParams
import im.dlg.platform.navigation.domain.ChatsForForwardingParams
import im.dlg.platform.navigation.domain.ContactsHostParams
import im.dlg.platform.navigation.domain.ExitParams
import im.dlg.platform.navigation.domain.FullScreenParams
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
import im.dlg.platform.navigation.presentation.GroupProfileUseCaseNavigator
import im.dlg.platform.navigation.presentation.JazzCommunicationNavigator
import im.dlg.platform.navigation.presentation.PeerResolverNavigator
import im.dlg.platform.navigation.presentation.SearchProfileHostNavigator
import im.dlg.platform.navigation.presentation.SimpleNavigator
import im.dlg.platform.navigation.presentation.UserProfileNavigator
import ru.sberbank.sdakit.messenger.integration.domain.MessengerNavigators
import ru.sberbank.sdakit.messenger.integration.domain.sdk.SmartMessengerSDK
import java.lang.ref.WeakReference

/**
 *
 *
 * @author Алексей Соколов on 06.07.2022
 */
internal object MessengerDemoNavigators : MessengerNavigators {
    private val empty = EmptyCommonNavigator { activityContext?.get() }

    var messengerNavigation: SimpleMessengerNavigation? = null
    var activityContext: WeakReference<Context>? = null

    override val chatNavigator: ChatNavigator
        get() = object : ChatNavigator {
            override fun openChat(paramsIn: ChatParams) {
                val screenId = "Chat peer = ${paramsIn.peer.peerId}"
                showScreen(
                    screenId,
                    SmartMessengerSDK
                        .screenFactories
                        .messagingFragmentsFactory
                        .newMessagingFragment(paramsIn)
                )
            }

            override fun openProfileGroup(paramsIn: ProfileGroupParams) {
                val screenId = "GroupProfile = ${paramsIn.peerId}"
                showScreen(
                    screenId,
                    SmartMessengerSDK
                        .screenFactories
                        .profileGroupFragmentsFactory
                        .newProfileGroupFragment(paramsIn)
                )
            }

            override fun openContactsToForward(paramsIn: ChatsForForwardingParams) {
                TODO("Not yet implemented")
            }

            override fun openUserProfile(paramsIn: UserProfileParams) {
                val screenId = "UserProfile = ${paramsIn.peerId}"
                showScreen(
                    screenId,
                    SmartMessengerSDK
                        .screenFactories
                        .userProfileApiFragmentsFactory
                        .newUserProfileFragment(paramsIn)
                )
            }

            override fun getFullScreenIntent(paramsIn: FullScreenParams): Intent {
                TODO("Not yet implemented")
            }

            override fun exit(exitParams: ExitParams) {
                closeScreen()
            }
        }
    override val forwardingNavigator: ChatsForForwardingNavigator
        get() = empty
    override val contactsHostNavigator: ContactsHostNavigator
        get() = object : ContactsHostNavigator {
            override fun openCreatedGroup(paramsIn: ChatParams) {
                TODO("Not yet implemented")
            }

            override fun exit(exitParams: ExitParams) {
                closeScreen()
            }
        }
    override val contactsNavigator: ContactsNavigator
        get() = empty
    override val deeplinkNavigator: DeepLinkNavigator
        get() = empty
    override val dialogListNavigator: DialogListNavigator
        get() = object : DialogListNavigator {
            override fun openChat(paramsIn: ChatParams) {
                val screenId = "Chat peer = ${paramsIn.peer.peerId}"
                showScreen(
                    screenId,
                    SmartMessengerSDK
                        .screenFactories
                        .messagingFragmentsFactory
                        .newMessagingFragment(paramsIn)
                )
            }

            override fun openContactsHost(paramsIn: ContactsHostParams) {
                val screenId = "Contacts"
                showScreen(
                    screenId,
                    SmartMessengerSDK
                        .screenFactories
                        .contactsHostFragmentsFactory
                        .newContactsHostFragment(paramsIn)
                )
            }

            override fun openSearchHost(paramsIn: SearchHostParams) {
                closeScreen()
            }
        }
    override val groupProfileUseCaseNavigator: GroupProfileUseCaseNavigator
        get() = object : GroupProfileUseCaseNavigator {
            override fun exitOnDelete(exitParams: ExitParams) {
                closeScreen()
            }

            override fun exit(exitParams: ExitParams) {
                closeScreen()
            }
        }
    override val groupProfileNavigator: GroupProfileNavigator
        get() = empty
    override val jazzCommunicationNavigator: JazzCommunicationNavigator
        get() = empty
    override val peerResolverNavigator: PeerResolverNavigator
        get() = empty
    override val searchProfileHostNavigator: SearchProfileHostNavigator
        get() = empty
    override val userProfileNavigator: UserProfileNavigator
        get() = empty
    override val simpleNavigator: SimpleNavigator
        get() = object : SimpleNavigator {
            override fun exit(exitParams: ExitParams) {
                closeScreen()
            }
        }

    private fun showScreen(screenId: String, fragment: Fragment) {
        messengerNavigation?.openScreen(screenId, fragment)
            ?: throw IllegalStateException("MessengerNavigation is NULL")
    }

    private fun closeScreen() {
        messengerNavigation?.closeScreen() ?: throw IllegalStateException("messengerNavigation is NULL")
    }
}
