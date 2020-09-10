package com.nhapps.zennote.utils

import android.view.View
import androidx.appcompat.app.AppCompatActivity

object HideBar {

        // Hides the Android OS TaskBar within the given context
        fun apply(context: AppCompatActivity){
            context.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
            context.actionBar?.hide()
        }

}