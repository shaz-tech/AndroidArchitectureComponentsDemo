package shaz.livedata_locationupdate.livedata

import android.arch.lifecycle.LiveData
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import shaz.livedata_locationupdate.pojo.Battery

/**
 * Created by ${Shahbaz} on 08-11-2017
 */
class BatteryLevelLiveData : LiveData<Battery> {

    private var receiver: BroadcastReceiver
    private var context: Context

    constructor(context: Context) {
        this.context = context
        this.receiver = BatteryBroadcastReceiver()
    }

    override fun onActive() {
        super.onActive()
        registerReceiver()
    }

    override fun onInactive() {
        super.onInactive()
        unregisterReceiver()
    }

    private fun registerReceiver() {
        context?.let {
            val intentFilter = IntentFilter()
            intentFilter.addAction(Intent.ACTION_BATTERY_LOW)
            intentFilter.addAction(Intent.ACTION_BATTERY_OKAY)
            context.registerReceiver(receiver, intentFilter)
        }
    }

    private fun unregisterReceiver() {
        context?.let {
            context.unregisterReceiver(receiver)
        }
    }

    private class BatteryBroadcastReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.let {
                val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
                val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
                val pluggedInStatus = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)
                val percentage = (level / scale).toFloat()
                val isCharging = pluggedInStatus <= 0
                val battery = Battery(percentage, isCharging)
            }
        }
    }

    private  fun statusChanged(data: Battery){
        value = data
    }
}