package shaz.livedata_locationupdate.pojo

/**
 * Created by ${Shahbaz} on 08-11-2017
 */
class Battery(percentage: Float, charging: Boolean) {
    private var percentage: Float = 0f
    private var charging: Boolean = false

    fun getPercentage(): Float {
        return percentage
    }

    fun isCharging(): Boolean{
        return charging
    }
}