package com.devsync.pixelpaletteandroid.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Environment
import android.util.Log
import com.devsync.pixelpaletteandroid.model.PixelColor
import com.devsync.pixelpaletteandroid.model.PaletteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object PixelPaletteUtil {

    fun downscaleBitmap(bitmap: Bitmap, factor: Int): Bitmap {
        val image: Bitmap
        if (factor == 0) {
            Log.e("", "Downscaling Factor value should be between 0 to N, So Using default value of 1")
            image = Bitmap.createScaledBitmap(bitmap, bitmap.width / 1, bitmap.height / 1, true)
        } else {
            image = Bitmap.createScaledBitmap(bitmap, bitmap.width / factor, bitmap.height / factor, true)
        }
        return image
    }

    // Function to get a contrasting color for text
    internal fun getContrastingColor(color: PixelColor): Int {
        val luminance = 0.2126 * color.red + 0.7152 * color.green + 0.0722 * color.blue
        return if (luminance > 128) Color.BLACK else Color.WHITE
    }

    suspend fun displayPalette(
        palette: List<PixelColor>,
        colorWidth: Int,
        colorHeight: Int,
        imageWidth: Int,
        imageHeight: Int,
        path: String? = null,
        fileName: String? = null,
        isVertical: Boolean,
    ): PaletteModel = withContext(Dispatchers.Default) {
        val paletteBitmap = Bitmap.createBitmap(imageWidth, imageHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(paletteBitmap)
        val paint = Paint().apply {
            textSize = 12f
            isAntiAlias = true
        }

        if (isVertical) {
            val numRows = imageHeight / colorHeight
            for (i in palette.indices) {
                if (i >= numRows) break
                val color = palette[i]
                val x = (imageWidth - colorWidth) / 2
                val y = i * colorHeight

                paint.color = color.toInt()
                canvas.drawRect(
                    x.toFloat(),
                    y.toFloat(),
                    (x + colorWidth).toFloat(),
                    (y + colorHeight).toFloat(),
                    paint
                )

                val textColor = getContrastingColor(color)
                paint.color = textColor

                val hexCode = String.format(
                    "#%02X%02X%02X",
                    color.red,
                    color.green,
                    color.blue
                )
                canvas.drawText(hexCode, (x + 5).toFloat(), (y + colorHeight - 10).toFloat(), paint)
            }
        } else {
            val numColumns = imageWidth / colorWidth
            for (i in palette.indices) {
                if (i >= numColumns) break
                val color = palette[i]
                val x = i * colorWidth
                val y = (imageHeight - colorHeight) / 2

                paint.color = color.toInt()
                canvas.drawRect(
                    x.toFloat(),
                    y.toFloat(),
                    (x + colorWidth).toFloat(),
                    (y + colorHeight).toFloat(),
                    paint
                )

                val textColor = getContrastingColor(color)
                paint.color = textColor

                val hexCode = String.format(
                    "#%02X%02X%02X",
                    color.red,
                    color.green,
                    color.blue
                )
                canvas.drawText(hexCode, (x + 5).toFloat(), (y + colorHeight - 10).toFloat(), paint)
            }
        }

        try {
            val outputFile = if (!fileName.isNullOrEmpty()) {
                if (path != null) {
                    File(path, fileName)
                } else {
                    File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), fileName)
                }
            } else {
                val time = System.currentTimeMillis()
                val randomNumber = "${(1000..1000000).random()}_${(0..2000000).random()}"
                val name = "${time}_${randomNumber}.png"
                if (path != null) {
                    File(path, name)
                } else {
                    File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), name)
                }
            }

            withContext(Dispatchers.IO) {
                FileOutputStream(outputFile).use { out ->
                    paletteBitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
                }
            }
            Log.d("Display Palette", "Palette saved to ${outputFile.absolutePath}")
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return@withContext PaletteModel(palette, paletteBitmap)
    }

}
