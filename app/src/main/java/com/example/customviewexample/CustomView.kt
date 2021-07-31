package com.example.customviewexample

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

class CustomView @JvmOverloads constructor(
    context: Context, attrs:
    AttributeSet? = null
) : FrameLayout(context, attrs) {

    companion object {
        const val DEFAULT_COLOR_RES = R.color.defaultColorCv
    }

    init {

    }
}