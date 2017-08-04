package com.arjunraghavan.android.registerloginscreenconcept.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arjunraghavan.android.registerloginscreenconcept.R
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView: ViewGroup =
                inflater?.inflate(R.layout.fragment_register, container, false) as ViewGroup
        // Rotate all the views inside the fragment by 180 degrees to counteract the ViewPager's
        //  being rotated as such (otherwise everything'd be upside down).
        rootView.rotation = 180f

        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        register_textview.setOnClickListener {
            // Register
        }

    }
}
