package com.example.wether_app.utils

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.example.wether_app.R

class PieChart(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private var mShowText: Boolean
    private var textPos: Int
    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.PieChart,
            0, 0).apply {

            try {
                mShowText = getBoolean(R.styleable.PieChart_showText, false)
                textPos = getInteger(R.styleable.PieChart_labelPosition, 0)
            } finally {
                recycle()
            }
        }
    }

    fun isShowText(): Boolean {
        return mShowText
    }

    fun setShowText(showText: Boolean) {
        mShowText = showText
        invalidate()
        requestLayout()
    }

}
