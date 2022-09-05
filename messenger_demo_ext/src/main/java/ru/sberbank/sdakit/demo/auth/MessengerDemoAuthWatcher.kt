package ru.sberbank.sdakit.demo.auth

import android.os.Handler
import io.reactivex.Observable
import io.reactivex.Single
import ru.sberbank.sdakit.messenger.integration.domain.auth.AuthEvent
import ru.sberbank.sdakit.messenger.integration.domain.auth.AuthType
import ru.sberbank.sdakit.messenger.integration.domain.auth.MessengerAuthWatcher

object MessengerDemoAuthWatcher : MessengerAuthWatcher {
    @Suppress("MaxLineLength")
    private val mockedToken =
        "Bearer eyJhbGciOiJIUzI1NiIsImtpZCI6IjEifQ.eyJjbGllbnRfaWQiOiJiMWYwZjBjNi1mY2IwLTRlY2UtODM3NC02YjYxNGViZTNkNDIiLCJzdWIiOiJhNjBlMGJjOGFkYzU1NTg4N2ExMmQ2MDg3NTlhNTE4OTU1ODExNjI0ZGUxZDU3YWRlYTU2ZDg0OTdiZjIxNDdiNTM5YmU5MjcwMDQyNjI5OCIsImp0aSI6IkJEMDI0QTU5LUIzNEMtMjQyRC1DMjNELTMxMzMzMDFFOEEwMiIsImlzcyI6Imh0dHBzOi8vb25saW5lLnNiZXJiYW5rLnJ1L0NTQUZyb250L2luZGV4LmRvIiwiaWF0IjoxNjYxMTgxOTQwLCJleHAiOjE2Njg5NTc5NDAsImF1ZCI6ImIxZjBmMGM2LWZjYjAtNGVjZS04Mzc0LTZiNjE0ZWJlM2Q0MiJ9.DsgNd5Lggs5WMbryQjYzW8QlkO9rGYdtxV44KsiMwAs"

    override val authType: AuthType
        get() = AuthType.EXTERNAL

    override val authEvent: Observable<AuthEvent>
        get() = Observable.create { emitter ->
            Handler().postDelayed({
                emitter.onNext(AuthEvent.Login(mockedToken))
            }, 3000)
        }

    override val currentBearerToken: String
        get() = mockedToken

    override fun refreshBearerToken(): Single<Boolean> {
        return TODO("implement logic here")
    }
}
