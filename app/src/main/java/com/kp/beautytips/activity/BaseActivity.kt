package com.kp.beautytips.activity

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    val activity: Activity
        get() = this
}