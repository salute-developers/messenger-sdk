package ru.sberbank.sdakit.demo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import io.reactivex.rxjava3.subjects.PublishSubject

object BatteryReceiver : BroadcastReceiver() {
    val levelObservable = PublishSubject.create<Int>()

    override fun onReceive(context: Context?, intent: Intent?) {
        val level = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1
        val scale = intent?.getIntExtra(BatteryManager.EXTRA_SCALE, -1) ?: -1
        val lvl = (level * 100 / scale.toFloat()).toInt()
        levelObservable.onNext(lvl)
    }
}
