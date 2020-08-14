package mobi.appcent.onlinehaber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mobi.appcent.onlinehaber.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //fragmentCreate()

    }


    fun fragmentCreate() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val firstFragment = HomeFragment()
        fragmentTransaction.add(R.id.frameLayout, firstFragment).commit()


    }
}