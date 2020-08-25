package mobi.appcent.onlinehaber.util

import android.app.Activity
import android.content.Context

class SharedPreferences() {
    fun save(context: Context, text: String?) {
        val shared = context.getSharedPreferences("myPreferences", Context.MODE_PRIVATE)
        shared.edit().putString("value", text).commit()
    }

    fun getValue(context: Context): String {
        val shared = context.getSharedPreferences("myPreferences", Context.MODE_PRIVATE)
        var values = shared.getString("value", "")
        return values.toString()
    }
}