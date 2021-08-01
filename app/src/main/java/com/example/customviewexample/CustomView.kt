package com.example.customviewexample

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout

class CustomView @JvmOverloads constructor(
    context: Context, attrs:
    AttributeSet? = null
) : FrameLayout(context, attrs) {

    companion object {
        const val DEFAULT_COLOR_RES = R.color.defaultColorCv
        const val MIN_SIZE = 20
        const val MAX_SIZE = 50
    }

    private val drawablesList  = listOf(
        R.drawable.bg_circle,
        R.drawable.bg_rectangle,
        R.drawable.bg_rectangle_rounded,
    )
    private var defaultColorRes = DEFAULT_COLOR_RES
    private var colorsList: List<Int> = listOf()

    init {
        obtainAttrs(context, attrs)
        initViews()
    }

    private fun obtainAttrs(context: Context, attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomView)

        defaultColorRes = typedArray.getResourceId(R.styleable.CustomView_defaultColor, DEFAULT_COLOR_RES)

        typedArray.recycle()
    }

    private fun initViews() {
        setBackgroundResource(defaultColorRes)
    }

    private fun setColorList(colorsList: List<Int>) {
        this.colorsList = colorsList
    }
}