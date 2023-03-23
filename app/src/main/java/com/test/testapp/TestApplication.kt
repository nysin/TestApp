package com.test.testapp

import android.app.Application

class TestApplication:Application (){


    override fun onCreate() {
        super.onCreate()
        mApplication = this
    }
    companion object {
        lateinit var mApplication:Application
    }
}