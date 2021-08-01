package com.example.customviewexample

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var countViewsTv: TextView? = null

    private val colorsView = listOf(
        R.color.color_1,
        R.color.color_2,
        R.color.color_3,
        R.color.color_4,
        R.color.color_5
    )

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countViewsTv = findViewById(R.id.count_views_tv)

        findViewById<CustomView>(R.id.custom_view).apply {
            setColorList(colorsView)
            onChangeEvent = { count ->
                countViewsTv?.text = count.toString()
                if (count == CustomView.MAX_COUNT_VIEWS) {
                    Toast.makeText(this@MainActivity, "Game over", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}