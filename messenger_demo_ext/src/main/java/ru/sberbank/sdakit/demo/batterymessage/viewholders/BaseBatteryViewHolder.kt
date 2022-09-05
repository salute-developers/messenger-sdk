package ru.sberbank.sdakit.demo.batterymessage.viewholders

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import im.dlg.platform.message.custom.domain.CustomMessageHandler
import io.reactivex.rxjava3.disposables.Disposable
import org.json.JSONObject
import ru.sberbank.sdakit.demo.BatteryReceiver
import ru.sberbank.sdakit.demo.R

const val MAX_SIZE = 100

/**
 * @author Алексей Соколов on 20.07.2022
 */
internal open class BaseBatteryViewHolder(
    private val rootId: Int,
) : CustomMessageHandler.CustomMessageViewHolder {
    private lateinit var root: View
    private lateinit var level: TextView
    private lateinit var status: TextView
    private lateinit var vView: View

    private val animatorSet = AnimatorSet()

    private lateinit var batteryDisposable: Disposable

    override fun create(parent: FrameLayout) {
        root = LayoutInflater.from(parent.context).inflate(rootId, parent)
        level = root.findViewById(R.id.vLevel)
        status = root.findViewById(R.id.vStatus)
        vView = root.findViewById(R.id.vView)
    }

    override fun bind(parent: FrameLayout, message: JSONObject) {
        batteryDisposable = BatteryReceiver.levelObservable.subscribe {
            level.text = level.text.toString() + it.toString() + "\n"

            status.isVisible = when {
                it < 20 || it > 80 -> true
                else -> false
            }

            when {
                it < 20 -> {
                    status.text = status.context.getString(R.string.custom_message_battery_low)
                    status.setBackgroundColor(
                        ContextCompat.getColor(status.context, android.R.color.holo_red_dark)
                    )
                }
                it > 80 -> {
                    status.text = status.context.getString(R.string.custom_message_battery_full)
                    status.setBackgroundColor(
                        ContextCompat.getColor(status.context, android.R.color.holo_green_dark)
                    )
                }
            }
        }

        status.setOnClickListener { startAnimation() }
    }

    override fun unbind(parent: FrameLayout) {
        batteryDisposable.dispose()

        animatorSet.cancel()
    }

    override fun markHidden(message: JSONObject): Boolean {
        return message.optBoolean("is_edit_mark_hidden")
    }

    override fun statusHidden(message: JSONObject): Boolean {
        return message.optBoolean("is_status_hidden")
    }

    override fun authorHidden(message: JSONObject): Boolean {
        return message.optBoolean("is_author_hidden")
    }

    private fun startAnimation() {
        val valueAnimator = ValueAnimator.ofInt(0, MAX_SIZE, 0).apply {
            duration = 1000
            addUpdateListener {
                val newValue: Int = it.animatedValue as Int
                val newParams = vView.layoutParams
                newParams.height = newValue
                vView.layoutParams = newParams
            }
            interpolator = DecelerateInterpolator()
            repeatCount = 10
        }

        animatorSet.interpolator = AccelerateDecelerateInterpolator()
        animatorSet.play(valueAnimator)
        animatorSet.start()
    }
}
