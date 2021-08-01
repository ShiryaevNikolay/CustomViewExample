package com.example.customviewexample

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat

class CustomView @JvmOverloads constructor(
    context: Context, attrs:
    AttributeSet? = null
) : FrameLayout(context, attrs) {

    companion object {
        const val DEFAULT_COLOR_RES = R.color.defaultColorCv
    }

    private var defaultColorRes = DEFAULT_COLOR_RES

    init {
        obtainAttrs(context, attrs)
        initViews()
    }

    private fun obtainAttrs(context: Context, attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomView)

        defaultColorRes = typedArray.getResourceId(R.styleable.CustomView_defaultColor, DEFAULT_COLOR_RES)

        typedArray.recycle()
    }

    @SuppressLint("ResourceType")
    private fun initViews() {
        setBackgroundResource(defaultColorRes)
    }
}