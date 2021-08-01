package com.example.customviewexample

import android.content.Context
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.marginTop
import kotlin.math.roundToInt

class CustomView @JvmOverloads constructor(
    context: Context, attrs:
    AttributeSet? = null
) : FrameLayout(context, attrs), View.OnTouchListener {

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
    private val views = mutableListOf<View>()
    private var defaultColorRes = DEFAULT_COLOR_RES
    private var colorsList = emptyList<Int>()

    init {
        obtainAttrs(context, attrs)
        initViews()
        setOnTouchListener(this)
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

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        val eventX = event.x.toInt()
        val eventY = event.y.toInt()

        val size = (MIN_SIZE..MAX_SIZE).random()
        val drawableIndex = (0..2).random()

        val newView = View(context).apply {
            layoutParams = LayoutParams(
                dpToPx(size),
                dpToPx(size)
            ).apply {
                val x = eventX - (width / 2)
                val y = eventY - (height / 2)
                setMargins(x, y, 0, 0)
            }
            setBackgroundResource(drawablesList[drawableIndex])
        }

        addView(newView)

        return true
    }

    private fun dpToPx(dp: Int): Int {
        val displayMetrics: DisplayMetrics = context.resources.displayMetrics
        return (dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
    }
}