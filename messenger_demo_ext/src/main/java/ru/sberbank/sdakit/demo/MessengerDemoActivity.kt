package ru.sberbank.sdakit.demo

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import ru.sberbank.sdakit.core.utils.view.viewBinding
import ru.sberbank.sdakit.demo.databinding.ActivityMessengerDemoBinding
import ru.sberbank.sdakit.demo.navigation.MessengerDemoNavigators
import ru.sberbank.sdakit.demo.navigation.SimpleMessengerNavigation
import ru.sberbank.sdakit.messenger.background.BackgroundDrawable
import ru.sberbank.sdakit.messenger.background.BackgroundGradientOnShader
import java.lang.ref.WeakReference

class MessengerDemoActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMessengerDemoBinding::inflate)

    private val simpleMessengerNavigation = SimpleMessengerNavigation()

    // возможно не нужен
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (!simpleMessengerNavigation.onBackPressed()) {
                isEnabled = false
                this@MessengerDemoActivity.onBackPressed()
                isEnabled = true
            }
        }
    }

    private var mPainter: BackgroundGradientOnShader? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFullScreen()
        setContentView(binding.root)
        applyCommonBackground(binding.root)

        initMessengerNavigators()

        if (savedInstanceState == null) {
            initMainFragment()
        }
    }

    override fun onResume() {
        super.onResume()
        onBackPressedDispatcher.addCallback(onBackPressedCallback)
    }

    override fun onPause() {
        onBackPressedCallback.remove()
        super.onPause()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        mPainter?.onConfigurationChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        simpleMessengerNavigation.detach()
        mPainter?.dispose()
    }

    private fun setFullScreen() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    private fun applyCommonBackground(rootView: View?) {
        rootView?.apply {
            mPainter?.dispose()
            mPainter = BackgroundGradientOnShader(this@MessengerDemoActivity)
            mPainter?.let { painter ->
                background = BackgroundDrawable(
                    owningView = this,
                    painter = painter
                )
            }
        }
    }

    private fun initMessengerNavigators() {
        MessengerDemoNavigators.messengerNavigation = simpleMessengerNavigation
        lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) {
                MessengerDemoNavigators.activityContext = WeakReference(this@MessengerDemoActivity)
            }

            override fun onDestroy(owner: LifecycleOwner) {
                MessengerDemoNavigators.activityContext = null
            }
        })

        simpleMessengerNavigation.attach(
            supportFragmentManager,
            R.id.messenger_demo_container,
            mPainter
        )
    }

    private fun initMainFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.messenger_demo_container, MainFragment())
            .commit()
    }
}
