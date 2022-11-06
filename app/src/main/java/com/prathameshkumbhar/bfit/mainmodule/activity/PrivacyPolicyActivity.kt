package com.prathameshkumbhar.bfit.mainmodule.activity

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.prathameshkumbhar.bfit.databinding.ActivityPrivacyPolicyBinding

class PrivacyPolicyActivity : AppCompatActivity() {

    lateinit var binding : ActivityPrivacyPolicyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPrivacyPolicyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.privacyPolicyWebView.webViewClient = WebViewClient()
        binding.privacyPolicyWebView.loadUrl("https://sites.google.com/pvppcoe.ac.in/b-fit/home")

        //Terms and condition in future use. - https://pages.flycricket.io/b-fit/terms.html

        binding.privacyPolicyWebView.settings.javaScriptEnabled = true
        binding.privacyPolicyWebView.settings.setSupportZoom(true)
    }

    override fun onBackPressed() {
        if (binding.privacyPolicyWebView.canGoBack())
            binding.privacyPolicyWebView.goBack()
        else
            super.onBackPressed()
    }

}