package com.arjunraghavan.android.registerloginscreenconcept.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import android.support.v7.app.AppCompatActivity
import com.arjunraghavan.android.registerloginscreenconcept.R
import com.arjunraghavan.android.registerloginscreenconcept.fragments.LoginFragment
import com.arjunraghavan.android.registerloginscreenconcept.fragments.RegisterFragment
import kotlinx.android.synthetic.main.content_register_login.*

class RegisterLoginActivity : AppCompatActivity() {

    //TODO: LOW PRIORITY
    // - Implement cool transitions on the register/login screen so that the UX is improved

    lateinit var pagerAdapter: PagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_login)

        // Set the viewpager adapter to the inner class ScreenSlidePagerActivity below.
        pagerAdapter = ScreenSlidePagerActivity(supportFragmentManager)
        register_login_viewpager.adapter = pagerAdapter
        // Rotate the viewpager 180 degrees (so that the user swipes down to advance in the
        //  viewpager instead of up).
        register_login_viewpager.rotation = 180f
        // Set this limit so that the app doesn't need to keep too many pages in memory.
        register_login_viewpager.offscreenPageLimit = 1
    }

    private inner class ScreenSlidePagerActivity(fm: FragmentManager) :
            FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int): Fragment? {
            when(position%2) {
                0 -> return LoginFragment() // If the position is even, login.
                1 -> return RegisterFragment() // If the position is odd, register.
                else -> return null
            }
        }

        override fun getCount(): Int {
            return 100 // This could be Int.MAX_VALUE but that causes the app to load slower.
                       //  100 pages seems reasonable. Could be more.
        }

    }
}
