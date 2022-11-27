package com.smartshopper.smart_shopper.utils

import android.app.Activity
import android.content.Context
import androidx.core.content.res.ResourcesCompat
import com.smartshopper.smart_shopper.R
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

object Utils {
    fun errorToast(context: Context,message: String){
        MotionToast.createColorToast(
            context as Activity,
            "Opps",
            message,
            MotionToastStyle.ERROR,
            MotionToast.GRAVITY_BOTTOM,
            MotionToast.LONG_DURATION,
            ResourcesCompat.getFont(context, R.font.proximanovacond_medium)
        )
    }
}