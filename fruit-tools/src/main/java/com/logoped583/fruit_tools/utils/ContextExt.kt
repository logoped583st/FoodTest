package com.logoped583.fruit_tools.utils

import android.content.Context
import android.util.DisplayMetrics

fun Context.dpToPx(dp: Int) = (dp * this.resources.displayMetrics.density).toInt()

fun Context.pxToDp(px: Int) = px / (this.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)