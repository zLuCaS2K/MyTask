package com.lucasprojects.mytask.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lucasprojects.mytask.R
import com.lucasprojects.mytask.activities.LicensesActivity
import com.lucasprojects.mytask.constants.LinkConstants
import com.lucasprojects.mytask.util.Utils
import kotlinx.android.synthetic.main.fragment_about.*

class AboutFragment : Fragment(), View.OnClickListener {

    private lateinit var mViewRoot: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mViewRoot = inflater.inflate(R.layout.fragment_about, container, false)
        return mViewRoot
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnGitHubApp.setOnClickListener(this)
        btnGitHubDeveloper.setOnClickListener(this)
        btnLicenses.setOnClickListener(this)
        imageKotlin.setOnClickListener(this)
        backgroundRipple.startRippleAnimation()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnGitHubApp -> Utils.openBrower(mViewRoot.context, LinkConstants.LINKAPP)
            R.id.btnGitHubDeveloper -> Utils.openBrower(mViewRoot.context, LinkConstants.LINKDEVELOPER)
            R.id.btnLicenses -> startActivity(Intent(mViewRoot.context, LicensesActivity::class.java))
            R.id.imageKotlin -> Utils.openBrower(mViewRoot.context, LinkConstants.LINKKOTLINSITE)
        }
    }
}
