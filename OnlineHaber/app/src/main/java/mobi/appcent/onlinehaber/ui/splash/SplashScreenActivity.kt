package mobi.appcent.onlinehaber.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mobi.appcent.onlinehaber.ui.activity.MainActivity
import mobi.appcent.onlinehaber.R
import java.lang.Exception

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val background = object : Thread() {
            override fun run() {
                try {
                    Thread.sleep(3000)
                    val intent = Intent(
                        baseContext,
                        MainActivity::class.java
                    )
                    startActivity(intent)
                } catch (e: Exception) {
                    e.printStackTrace()

                }
            }

        }
        background.start()
    }
}