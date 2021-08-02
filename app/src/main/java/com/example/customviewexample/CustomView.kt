package com.example.customviewexample

import android.content.Context
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import kotlin.math.roundToInt

class CustomView @JvmOverloads constructor(
    context: Context, attrs:
    AttributeSet? = null
) : FrameLayout(context, attrs), View.OnTouchListener {

    var onChangeEvent: ((Int) -> Unit)? = null

    companion object {
        const val MAX_COUNT_VIEWS = 10
        const val DEFAULT_COLOR_RES = R.color.defaultColorCv
        const val MIN_SIZE = 20
        const val MAX_SIZE = 50
    }

    private val drawablesList  = listOf(
        R.drawable.bg_circle,
        R.drawable.bg_rectangle,
        R.drawable.bg_rectangle_rounded,
    )
    private var countViews = 0
    private var defaultColorRes = DEFAULT_COLOR_RES
    private var colorsList = emptyList<Int>()

    init {
        obtainAttrs(context, attrs)
        initViews()
        setOnTouchListener(this)
    }

    fun setColorList(colorsView: List<Int>) {
        this.colorsList = colorsView
    }

    private fun obtainAttrs(context: Context, attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomView)

        defaultColorRes = typedArray.getResourceId(R.styleable.CustomView_defaultColor, DEFAULT_COLOR_RES)

        typedArray.recycle()
    }

    private fun initViews() {
        setBackgroundResource(defaultColorRes)
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN && countViews < MAX_COUNT_VIEWS) {
            val eventX = event.x.toInt()
            val eventY = event.y.toInt()

            val size = (MIN_SIZE..MAX_SIZE).random()
            val drawableIndex = (0..2).random()
            val colorView = if (colorsList.isNotEmpty()) {
                (colorsList.indices).random()
            } else {
                DEFAULT_COLOR_RES
            }

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
                backgroundTintList = ContextCompat.getColorStateList(context, colorsList[colorView])
            }

            addView(newView)
            countViews += 1
            onChangeEvent?.invoke(countViews)
        } else if (countViews == MAX_COUNT_VIEWS) {
            removeAllViews()
            countViews = 0
        }

        return true
    }

    private fun dpToPx(dp: Int): Int {
        val displayMetrics: DisplayMetrics = context.resources.displayMetrics
        return (dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
    }
}