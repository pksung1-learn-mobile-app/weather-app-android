package com.example.wether_app.utils

import android.content.Context
import android.graphics.Paint
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.util.AttributeSet
import android.view.View
import com.example.wether_app.R

class CircleImage(context: Context, attrs: AttributeSet) : View(context, attrs) {

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CircleImage,
            0, 0
        ).apply {
            recycle()
        }

    }



}