package com.devsync.pixelpaletteandroid.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import com.devsync.pixelpaletteandroid.model.PixelColor
import com.devsync.pixelpaletteandroid.utils.PixelPaletteUtil.getContrastingColor

class PaletteView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val paint = Paint().apply {
        textSize = 12f
        isAntiAlias = true
    }
    private var scaleFactor = 1f
    private val scaleGestureDetector = ScaleGestureDetector(context, ScaleListener())
    private val matrix = Matrix()
    private val inverseMatrix = Matrix()

    private var palette: List<PixelColor> = emptyList()
    private var colorWidth = 0
    private var colorHeight = 0
    private var imageWidth = 0
    private var imageHeight = 0
    private var isVertical = false

    private var bitmap: Bitmap? = null
    private var isShowingBitmap = false

    private var onColorClickListener: ((PixelColor) -> Unit)? = null

    fun setPalette(palette: List<PixelColor>, colorWidth: Int, colorHeight: Int, imageWidth: Int, imageHeight: Int, isVertical: Boolean) {
        this.palette = palette
        this.colorWidth = colorWidth
        this.colorHeight = colorHeight
        this.imageWidth = imageWidth
        this.imageHeight = imageHeight
        this.isVertical = isVertical
        this.isShowingBitmap = false
        invalidate()
    }

    fun setBitmap(bitmap: Bitmap) {
        this.bitmap = bitmap
        this.isShowingBitmap = true
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        matrix.reset()
        matrix.postScale(scaleFactor, scaleFactor)
        canvas.setMatrix(matrix)

        if (isShowingBitmap) {
            bitmap?.let { bmp ->
                val bmpWidth = bmp.width.toFloat()
                val bmpHeight = bmp.height.toFloat()
                val viewWidth = width.toFloat()
                val viewHeight = height.toFloat()
                val scaleX = viewWidth / bmpWidth
                val scaleY = viewHeight / bmpHeight
                val scale = minOf(scaleX, scaleY)
                matrix.postScale(scale, scale)
                canvas.drawBitmap(bmp, matrix, paint)
            }
        } else {
            palette.forEachIndexed { index, color ->
                val rectPaint = Paint().apply { color.toInt() }
                val x: Float
                val y: Float
                if (isVertical) {
                    x = (imageWidth - colorWidth) / 2f
                    y = index * colorHeight.toFloat()
                } else {
                    x = index * colorWidth.toFloat()
                    y = (imageHeight - colorHeight) / 2f
                }

                canvas.drawRect(x, y, x + colorWidth, y + colorHeight, rectPaint)

                val textColor = getContrastingColor(color)
                paint.color = textColor

                val hexCode = String.format("#%02X%02X%02X", color.red, color.green, color.blue)
                canvas.drawText(hexCode, x + 5, y + colorHeight - 10, paint)
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        scaleGestureDetector.onTouchEvent(event)
        val touchPoint = floatArrayOf(event.x, event.y)
        inverseMatrix.mapPoints(touchPoint)
        val x = touchPoint[0]
        val y = touchPoint[1]

        if (event.action == MotionEvent.ACTION_UP && !isShowingBitmap) {
            handleClick(x, y)
        }

        return true
    }

    private fun handleClick(x: Float, y: Float) {
        palette.forEachIndexed { index, color ->
            val rectX: Float
            val rectY: Float
            if (isVertical) {
                rectX = (imageWidth - colorWidth) / 2f
                rectY = index * colorHeight.toFloat()
            } else {
                rectX = index * colorWidth.toFloat()
                rectY = (imageHeight - colorHeight) / 2f
            }
            if (x >= rectX && x <= rectX + colorWidth && y >= rectY && y <= rectY + colorHeight) {
                onColorClickListener?.invoke(color)
                return
            }
        }
    }

    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            scaleFactor *= detector.scaleFactor
            scaleFactor = maxOf(0.1f, minOf(scaleFactor, 5.0f))
            invalidate()
            return true
        }
    }

    fun setOnColorClickListener(listener: (PixelColor) -> Unit) {
        onColorClickListener = listener
    }

}
