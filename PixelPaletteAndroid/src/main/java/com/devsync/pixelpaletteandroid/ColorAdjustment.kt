/*
 * Copyright 2024 Developer Syndicate
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Author: Sanjay S
 * Date: 03-08-2024
 */

package com.devsync.pixelpaletteandroid

import android.graphics.Bitmap
import android.graphics.Color
import com.devsync.pixelpaletteandroid.PixelConversionRGB.RGBtoHSL
import com.devsync.pixelpaletteandroid.view.PixelConversionHSL.HSLtoRGB

/**
 * The `ColorAdjustment` object provides methods to adjust the color properties of an image represented as a `Bitmap`.
 * It supports adjusting the color balance (Red, Green, Blue channels), brightness, contrast, and saturation of the image.
 */
object ColorAdjustment {

    /**
     * Adjusts the color balance by scaling the Red, Green, and Blue channels.
     *
     * @param bitmap The original bitmap to be color balanced.
     * @param redFactor Factor by which to scale the red channel (1.0 means no change).
     * @param greenFactor Factor by which to scale the green channel (1.0 means no change).
     * @param blueFactor Factor by which to scale the blue channel (1.0 means no change).
     * @return A new bitmap with the adjusted color balance.
     */
    fun adjustColorBalance(
        bitmap: Bitmap,
        redFactor: Float,
        greenFactor: Float,
        blueFactor: Float
    ): Bitmap? {
        val width = bitmap.width
        val height = bitmap.height
        var adjustedBitmap: Bitmap?
        bitmap.config?.let {
            adjustedBitmap = Bitmap.createBitmap(width, height, it)
            for (y in 0 until height) {
                for (x in 0 until width) {
                    val pixel = bitmap.getPixel(x, y)

                    val red = Color.red(pixel)
                    val green = Color.green(pixel)
                    val blue = Color.blue(pixel)

                    val newRed = (red * redFactor).toInt().coerceIn(0, 255)
                    val newGreen = (green * greenFactor).toInt().coerceIn(0, 255)
                    val newBlue = (blue * blueFactor).toInt().coerceIn(0, 255)

                    adjustedBitmap.setPixel(x, y, Color.rgb(newRed, newGreen, newBlue))
                }
            }
        }
        return null
    }

    /**
     * Adjusts the brightness of an image.
     *
     * @param bitmap The original bitmap to be adjusted.
     * @param brightnessFactor Factor by which to adjust the brightness. (1.0 means no change, greater than 1 increases brightness).
     * @return A new bitmap with the adjusted brightness.
     */
    fun adjustBrightness(bitmap: Bitmap, brightnessFactor: Float): Bitmap? {
        val width = bitmap.width
        val height = bitmap.height
        var adjustedBitmap: Bitmap?
        bitmap.config?.let {
            adjustedBitmap = Bitmap.createBitmap(width, height, it)
            for (y in 0 until height) {
                for (x in 0 until width) {
                    val pixel = bitmap.getPixel(x, y)

                    val red = Color.red(pixel)
                    val green = Color.green(pixel)
                    val blue = Color.blue(pixel)

                    val newRed = (red * brightnessFactor).toInt().coerceIn(0, 255)
                    val newGreen = (green * brightnessFactor).toInt().coerceIn(0, 255)
                    val newBlue = (blue * brightnessFactor).toInt().coerceIn(0, 255)

                    adjustedBitmap.setPixel(x, y, Color.rgb(newRed, newGreen, newBlue))
                }
            }
        }

        return null
    }

    /**
     * Adjusts the contrast of an image.
     *
     * @param bitmap The original bitmap to be adjusted.
     * @param contrastFactor Factor by which to adjust the contrast. (1.0 means no change, greater than 1 increases contrast).
     * @return A new bitmap with the adjusted contrast.
     */
    fun adjustContrast(bitmap: Bitmap, contrastFactor: Float): Bitmap? {
        val width = bitmap.width
        val height = bitmap.height
        var adjustedBitmap: Bitmap?
        bitmap.config?.let {
            adjustedBitmap = Bitmap.createBitmap(width, height, it)
            for (y in 0 until height) {
                for (x in 0 until width) {
                    val pixel = bitmap.getPixel(x, y)

                    val red = Color.red(pixel)
                    val green = Color.green(pixel)
                    val blue = Color.blue(pixel)

                    val newRed = ((red - 128) * contrastFactor + 128).toInt().coerceIn(0, 255)
                    val newGreen = ((green - 128) * contrastFactor + 128).toInt().coerceIn(0, 255)
                    val newBlue = ((blue - 128) * contrastFactor + 128).toInt().coerceIn(0, 255)

                    adjustedBitmap.setPixel(x, y, Color.rgb(newRed, newGreen, newBlue))
                }
            }
        }

        return null
    }

    /**
     * Adjusts the saturation of an image.
     *
     * @param bitmap The original bitmap to be adjusted.
     * @param saturationFactor Factor by which to adjust the saturation. (1.0 means no change, greater than 1 increases saturation).
     * @return A new bitmap with the adjusted saturation.
     */
    fun adjustSaturation(bitmap: Bitmap, saturationFactor: Float): Bitmap? {
        val width = bitmap.width
        val height = bitmap.height
        var adjustedBitmap: Bitmap? = null

        bitmap.config?.let {
            adjustedBitmap = Bitmap.createBitmap(width, height, it)

            for (y in 0 until height) {
                for (x in 0 until width) {
                    val pixel = bitmap.getPixel(x, y)

                    val red = Color.red(pixel)
                    val green = Color.green(pixel)
                    val blue = Color.blue(pixel)

                    val hsl = RGBtoHSL(red, green, blue)

                    hsl.saturation = (hsl.saturation * saturationFactor).coerceIn(0f, 1f)
                    val newRgb = HSLtoRGB(floatArrayOf(hsl.hue, hsl.saturation, hsl.lightness))

                    adjustedBitmap.setPixel(x, y, Color.rgb(newRgb[0], newRgb[1], newRgb[2]))
                }
            }
        }
        return adjustedBitmap
    }
}