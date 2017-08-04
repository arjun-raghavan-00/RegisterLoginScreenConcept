package com.arjunraghavan.android.registerloginscreenconcept.activities

import android.graphics.Point
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import android.support.v7.app.AppCompatActivity
import android.view.View
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

        val size: Point = Point()
        windowManager.defaultDisplay.getSize(size)

        pagerAdapter = ScreenSlidePagerActivity(supportFragmentManager)
        register_login_viewpager.adapter = pagerAdapter
        register_login_viewpager.rotation = 180f
        register_login_viewpager.offscreenPageLimit = 1
    }

    private inner class ScreenSlidePagerActivity(fm: FragmentManager) :
            FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int): Fragment? {
            when(position%2) {
                0 -> return LoginFragment()
                1 -> return RegisterFragment()
                else -> return null
            }
        }

        override fun getCount(): Int {
            return 100
        }

    }
}
