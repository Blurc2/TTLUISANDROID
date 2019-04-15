package peach.princess.my.net.ttluis.Utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.location.Location
import android.location.LocationManager
import android.net.ConnectivityManager
import android.os.BatteryManager
import android.os.Build
import android.os.Environment
import android.provider.Settings
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import peach.princess.my.net.ttluis.R
import peach.princess.my.net.ttluis.ui.Base.MyApplication
import java.util.*
import kotlin.collections.ArrayList

class Validation {


    companion object {
        // check gps
        fun checkGps(context: Context): Unit {

            if (!isGPSenabled(context) || getModeLocation(context) != 3) {
                Toast.makeText(context, "DEBE ENCENDER EL GPS", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }
        }

        fun isGPSenabled(context: Context): Boolean {
            var manager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            return manager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        }

        fun getModeLocation(context: Context): Int {
            var mode = 0
            try {
                mode = Settings.Secure.getInt(context.contentResolver, Settings.Secure.LOCATION_MODE)
            } catch (e: Settings.SettingNotFoundException) {
                Log.e("GSAPP", "" + e)
            }
            return mode
        }

        // check fake gps
        fun isLocationPlausible(location: Location?): Boolean {
            var lastMockLocation = null as Location?
            var numGoodReadings = 0
            if (location == null) return false
            var isMock = Build.VERSION.SDK_INT >= 18 && location.isFromMockProvider()
            if (isMock) {
                lastMockLocation = location
                numGoodReadings = 0
            } else {
                numGoodReadings = Math.min(numGoodReadings + 1, 1000000)// Prevent overflow
            }
            // We only clear that incident record after a significant show of good behavior
            if (numGoodReadings >= 20) lastMockLocation = null

            // If there's nothing to compare against, we have to trust it
            if (lastMockLocation == null) return true

            // And finally, if it's more than 1km away from the last known mock, we'll trust it
            var d = location.distanceTo(lastMockLocation)
            return (d > 1000)
        }

        // Date & Hour automatic
        fun checkDate(context: Context): Unit {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (Settings.Global.getInt(context.contentResolver, Settings.Global.AUTO_TIME, 0) == 0) {
                    Toast.makeText(context, "DEBE HABILITAR FECHA Y HORA AUTOMATICA", Toast.LENGTH_LONG).show()
                    var intent = Intent(Settings.ACTION_SETTINGS)
                    context.startActivity(intent)

                }
            }
        }

        // IMEI
        @SuppressLint("MissingPermission")
        public fun getIMEI(context: Context): String {
            var telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

            if (telephonyManager.getDeviceId() != null) {
                return telephonyManager.getDeviceId()
            }
            return "0"

        }

        fun getEmptyError(field: String) = when {
            field.isEmpty() || field.isEmpty() -> R.string.error_field_required
            else -> 0
        }

        // Battery level
        fun getBatteryLevel(context: Context): Float {
            var batterIntent = context.registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED)) as Intent
            var level = batterIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
            var scale = batterIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
            // Error checking that probably isn't needed but I added just in case.
            if (level == -1 || scale == -1) {
                return 50.0f
            }
            return (level / scale) * 100.0f

        }

        /*
        *  Comprueba que esten encendidos los provedores de geolocalizacion
        *  @return true si estan disponibles, false si estan apagados
        * */
        fun isGPSandNetWork(context: Context): Boolean {
            var flag = false
            var manager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER) && manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) flag = true
            return flag
        }

        // Check if the sdcard is mounted
        fun isSDCard(): Boolean {
            var estado = Environment.getExternalStorageState()
            return estado.equals(Environment.MEDIA_MOUNTED)
        }

        // Time zone where the cellphone is configured
        fun getTimeZone(): String {
            var timeZone = TimeZone.getDefault()
            return timeZone.id

        }

        fun isOnline(): Boolean {
            val cm = MyApplication.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = cm.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }


        fun <T> isArraylistEmpty(list: ArrayList<T>, objects: T, position: Int) {
            if (list.isEmpty() || list[position] == null) {
                list.add(objects)
            } else {
                list[position] = objects
            }
        }

    }


}