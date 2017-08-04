package com.arjunraghavan.android.registerloginscreenconcept.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arjunraghavan.android.registerloginscreenconcept.R
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView: ViewGroup =
                inflater?.inflate(R.layout.fragment_login, container, false) as ViewGroup
        rootView.rotation = 180f

        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        login_textview.setOnClickListener {
            // Log in
        }

    }
}
