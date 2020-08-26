package mobi.appcent.onlinehaber.util

import android.content.Context
import androidx.preference.PreferenceManager
import  android.content.SharedPreferences
import androidx.core.content.edit


class CustomSharedPreferences {


    companion object {
        private val PREFERENCES_TİME = "PREFERENCES_TİME"
        private var sharedPreferences: SharedPreferences? = null

        @Volatile
        private var instance: CustomSharedPreferences? = null
        private var lock = Any()
        operator fun invoke(context: Context): CustomSharedPreferences = instance ?: synchronized(
            lock
        )
        {
            instance ?: makeCustomSharedPreferences(context).also {
                instance = it
            }
        }

        private fun makeCustomSharedPreferences(context: Context): CustomSharedPreferences {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return CustomSharedPreferences()
        }
    }

    fun saveTime(time: Long) {
        sharedPreferences?.edit(commit = true) {
            putLong(PREFERENCES_TİME, time)
        }
    }

    fun getTime() = sharedPreferences?.getLong(PREFERENCES_TİME, 0)
}
