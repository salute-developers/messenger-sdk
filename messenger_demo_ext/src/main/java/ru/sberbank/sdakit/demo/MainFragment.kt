package ru.sberbank.sdakit.demo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ru.sberbank.sdakit.core.utils.view.viewBinding
import ru.sberbank.sdakit.demo.databinding.FragmentMainBinding
import ru.sberbank.sdakit.messenger.integration.domain.sdk.SmartMessengerSDK

class MainFragment : Fragment(R.layout.fragment_main) {
    private val binding by viewBinding(FragmentMainBinding::bind)

    private val dialogListFragmentsFactory = SmartMessengerSDK
        .screenFactories
        .dialogListFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            setCurrentFragment(
                { WelcomeFragment() },
                WELCOME_TAG,
                childFragmentManager
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_welcome_screen -> setCurrentFragment(
                    { WelcomeFragment() },
                    WELCOME_TAG,
                    childFragmentManager
                )
                R.id.menu_item_dialogs -> setCurrentFragment(
                    { dialogListFragmentsFactory.newDialogListFragment() },
                    MESSENGER_TAG,
                    childFragmentManager
                )
            }
            true
        }
    }

    private fun setCurrentFragment(
        func: () -> Fragment,
        tag: String,
        fragmentManager: FragmentManager,
    ) {
        val existedFragment = fragmentManager.findFragmentByTag(tag)
        fragmentManager.fragments.filter { it != existedFragment }
            .forEach { fragmentManager.beginTransaction().hide(it).commit() }
        if (existedFragment != null) {
            fragmentManager.beginTransaction()
                .show(existedFragment)
                .commit()
        } else {
            fragmentManager.beginTransaction()
                .add(R.id.messenger_menu_container, func.invoke(), tag)
                .commit()
        }
    }

    private companion object {
        const val WELCOME_TAG = "WELCOME_TAG"
        const val MESSENGER_TAG = "MESSENGER_TAG"
    }
}
