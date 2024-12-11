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

package com.devsync.pixelpalettejvm.utils

import com.devsync.pixelpaletteandroid.model.HSL
import com.devsync.pixelpaletteandroid.model.RGB
import kotlin.math.abs

object PixelConversionHSL {

    /**
     * Converts an HSL (Hue, Saturation, Lightness) color to its RGB (Red, Green, Blue) equivalent.
     * The conversion is done by first calculating the chroma, then determining the intermediate
     * values for red, green, and blue based on the hue, and finally adjusting these values
     * according to the lightness and saturation.
     *
     * The formula used here involves the following steps:
     * 1. Calculate chroma (c) using the lightness and saturation.
     * 2. Calculate an intermediate value (x) based on the hue.
     * 3. Calculate the match lightness value (m).
     * 4. Convert the resulting values to the 0-255 RGB color space.
     *
     * @param hsl The HSL color values that will be converted to RGB. The `hue` is between 0 and 360 degrees,
     *            `saturation` and `lightness` should be between 0 and 1.
     * @return The resulting `RGB` color after conversion. The RGB values are in the range 0-255.
     */
    fun HSLtoRGB(hsl: FloatArray): IntArray {
        val h = hsl[0]
        val s = hsl[1]
        val l = hsl[2]

        val c = (1 - abs(2 * l - 1f)) * s
        val x = c * (1 - abs((h / 60f) % 2 - 1))
        val m = l - c / 2

        var r = 0f
        var g = 0f
        var b = 0f

        when (h) {
            in 0f..60f -> {
                r = c
                g = x
                b = 0f
            }
            in 60f..120f -> {
                r = x
                g = c
                b = 0f
            }
            in 120f..180f -> {
                r = 0f
                g = c
                b = x
            }
            in 180f..240f -> {
                r = 0f
                g = x
                b = c
            }
            in 240f..300f -> {
                r = x
                g = 0f
                b = c
            }
            else -> {
                r = c
                g = 0f
                b = x
            }
        }

        val red = ((r + m) * 255).toInt()
        val green = ((g + m) * 255).toInt()
        val blue = ((b + m) * 255).toInt()

        return intArrayOf(red, green, blue)
    }

    fun HSLtoRGB(hsl: HSL): RGB {
        val c = (1 - abs(2 * hsl.lightness - 1f)) * hsl.saturation
        val x = c * (1 - abs((hsl.hue / 60f) % 2 - 1))
        val m = hsl.lightness - c / 2

        var r = 0f
        var g = 0f
        var b = 0f

        when {
            hsl.hue in 0f..60f -> {
                r = c
                g = x
                b = 0f
            }
            hsl.hue in 60f..120f -> {
                r = x
                g = c
                b = 0f
            }
            hsl.hue in 120f..180f -> {
                r = 0f
                g = c
                b = x
            }
            hsl.hue in 180f..240f -> {
                r = 0f
                g = x
                b = c
            }
            hsl.hue in 240f..300f -> {
                r = x
                g = 0f
                b = c
            }
            hsl.hue in 300f..360f -> {
                r = c
                g = 0f
                b = x
            }
        }

        r = (r + m) * 255
        g = (g + m) * 255
        b = (b + m) * 255

        return RGB(r.toInt(), g.toInt(), b.toInt())
    }

}