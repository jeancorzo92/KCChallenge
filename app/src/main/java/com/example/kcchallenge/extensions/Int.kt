package com.example.kcchallenge.extensions

import android.content.res.Resources
import android.util.DisplayMetrics

fun Int.dpToPx(): Int {
    return this * (Resources.getSystem().displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)
}