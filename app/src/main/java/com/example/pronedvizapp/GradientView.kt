package com.example.pronedvizapp

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.BlendMode
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ComposeShader
import android.graphics.LinearGradient
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.RadialGradient
import android.graphics.Shader
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi


class AnimatedGradientView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val paint = Paint()
    private val colors = intArrayOf(Color.parseColor("#F29E1F"), Color.parseColor("#000000"), Color.parseColor("#47D9D0"))
    private var colorPositions = floatArrayOf(0f, 0.5f, 1f)
    private var currentAngle = 0f

    init {
        val shader = LinearGradient(0f, 0f, width.toFloat(), height.toFloat(), colors, colorPositions, Shader.TileMode.CLAMP)
        paint.shader = shader
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
    }

    fun animateGradientColors() {
        val animator = ValueAnimator.ofFloat(0f, 360f)
        animator.addUpdateListener { valueAnimator ->
            currentAngle = valueAnimator.animatedValue as Float
            updateGradientColors()
            invalidate()
        }
        animator.duration = 30000
        animator.repeatCount = ValueAnimator.INFINITE
        animator.start()
    }

    private fun updateGradientColors() {
        val matrix = Matrix()
        matrix.setRotate(currentAngle, width.toFloat() / 2, height.toFloat() / 2)
        val shader = LinearGradient(0f, 0f, width.toFloat(), height.toFloat(), colors, colorPositions, Shader.TileMode.CLAMP)
        shader.setLocalMatrix(matrix)
        paint.shader = shader
    }
}