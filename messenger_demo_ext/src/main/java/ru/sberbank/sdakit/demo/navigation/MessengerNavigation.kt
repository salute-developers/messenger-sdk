package ru.sberbank.sdakit.demo.navigation

import android.service.autofill.Validators.not
import android.view.View
import androidx.core.view.ViewCompat.setBackground
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import im.dlg.platform.navigation.domain.BackPressHandler
import ru.sberbank.sdakit.messenger.background.BackgroundDrawable
import ru.sberbank.sdakit.messenger.background.BackgroundGradientOnShader

/**
 * @author Алексей Соколов on 06.07.2022
 */
internal interface MessengerNavigation {
    fun openScreen(screenId: String, fragment: Fragment)
    fun closeScreen()

    fun onBackPressed(): Boolean
    fun attach(
        fm: FragmentManager,
        containerId: Int,
        painter: BackgroundGradientOnShader?
    )

    fun detach()
}

internal class SimpleMessengerNavigation : MessengerNavigation {
    private var fm: FragmentManager? = null
    private var painter: BackgroundGradientOnShader? = null
    private var containerId: Int = 0

    override fun openScreen(screenId: String, fragment: Fragment) {
        fm?.beginTransaction()
            ?.add(containerId, fragment)
            ?.addToBackStack(screenId)
            ?.commit()

        fragment.doOnResume {
            fragment.view?.let {
                setBackground(it)
            }
        }
    }

    private fun setBackground(view: View) {
        painter?.let { shader ->
            view.background = BackgroundDrawable(
                owningView = view,
                painter = shader
            )
        }
    }

    override fun closeScreen() {
        fm?.popBackStack()
    }

    override fun attach(
        fm: FragmentManager,
        containerId: Int,
        painter: BackgroundGradientOnShader?
    ) {
        this.fm = fm
        this.containerId = containerId
        this.painter = painter
    }

    override fun detach() {
        fm = null
    }

    override fun onBackPressed(): Boolean {
        return fm?.findFragmentById(containerId)
            ?.let { fragment ->
                fragment is BackPressHandler && fragment.handleBackPress()
            } == true
    }
}

fun Fragment.doOnResume(block: () -> Unit) {
    lifecycle.addObserver(object : LifecycleEventObserver {
        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            if (event == Lifecycle.Event.ON_RESUME) {
                block()
                lifecycle.removeObserver(this)
            }
        }
    })
}
