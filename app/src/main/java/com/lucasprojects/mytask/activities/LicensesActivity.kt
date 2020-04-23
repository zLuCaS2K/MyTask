package com.lucasprojects.mytask.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lucasprojects.mytask.R
import kotlinx.android.synthetic.main.activity_licenses.*

class LicensesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_licenses)
        webViewLicenses.loadUrl("file:///android_asset/about.html")
    }
}
