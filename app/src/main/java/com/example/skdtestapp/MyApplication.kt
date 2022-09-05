package com.example.tagapplication

import android.app.Application
import com.lokalise.sdk.Lokalise

private const val API_KEY = "fdbb8b92152638e27227b1f71f74377aed11d69c"
private const val PROJECT_ID = "682860536290ecdf47e3c2.80705257"

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Initialise Lokalise SDK with projects SDK token and project id
        // It is important to call this right after the "super.onCreate()"
        // If you are using AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        // make sure it is called before LokaliseSDK.init()
        Lokalise.init(this, API_KEY, PROJECT_ID)

        // Add this only if you want to use pre-release bundles
        Lokalise.isPreRelease = true

        // Fetch the latest translations from Lokalise (can be called anywhere)
        Lokalise.updateTranslations()

        //Enable logging
        Lokalise.logsEnabled = true

        //Set the app's language manually
      Lokalise.setLocale("bg", "BG");
      //  Lokalise.setLocale("en", "US");
     //   Lokalise.setLocale("sr", "RS", "Latn")


    }

}