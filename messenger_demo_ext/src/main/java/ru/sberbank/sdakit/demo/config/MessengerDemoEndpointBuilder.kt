package ru.sberbank.sdakit.demo.config

import ru.sberbank.sdakit.messenger.integration.domain.MessengerEndpoint

/**
 * @author Алексей Соколов on 16.07.2021
 */
object MessengerDemoEndpointBuilder {

    fun build(): MessengerEndpoint {
        // IFT - "tls://eem-dialog-messenger-ift.online.sberbank.ru" to 7443
        // PSI - "tls://eem-dialog-messenger-psi.online.sberbank.ru" to 443
        // PROD - "tls://eem-dialog-messenger.online.sberbank.ru" to 443
        return MessengerEndpoint("eem-dialog-messenger.online.sberbank.ru", 443)
    }
}
