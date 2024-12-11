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
 * Date: 11-12-2024
 */
package com.devsync.pixelpaletteandroid

import android.graphics.Bitmap
import android.graphics.Color
import com.devsync.pixelpaletteandroid.model.ColorSensitivityModel

/**
 * The `ColorSensitivity` object provides functions for adjusting the color sensitivity of an image.
 * It allows modification of the Red, Green, and Blue (RGB) channels of a bitmap based on the provided
 * sensitivity values for each color channel. The sensitivity values determine how much influence each
 * color channel (Red, Green, Blue) has during the adjustment process.
 *
 * The adjustment is achieved by multiplying each RGB channel by its corresponding sensitivity factor.
 * The resulting RGB values are then clamped between 0 and 255 to ensure they remain within the valid color range.
 *
 * This object can be used for color-based image processing where fine control over each channel's sensitivity
 * is required, such as when applying color correction or creating custom image filters.
 *
 * Example usage:
 * ```
 * val sensitivity = ColorSensitivityModel(red = 1.2f, green = 0.8f, blue = 1f)
 * val adjustedBitmap = ColorSensitivity.adjustColorSensitivity(originalBitmap, sensitivity)
 * ```
 * In the above example:
 * - Red channel will be increased by 20% (1.2 times)
 * - Green channel will be reduced by 20% (0.8 times)
 * - Blue channel will remain unchanged (multiplied by 1)
 *
 * @see ColorSensitivityModel
 */
object ColorSensitivity {
    /**
     * Adjusts the color sensitivity of an image based on the provided sensitivity factors for each RGB channel.
     *
     * This function modifies the color channels (Red, Green, and Blue) of the input bitmap according to the
     * sensitivity values provided in the `ColorSensitivityModel`. Each color channel (Red, Green, Blue) will be
     * scaled by its corresponding sensitivity factor. The final RGB values are clamped between 0 and 255
     * to ensure valid color values for the resulting bitmap.
     *
     * @param bitmap The input bitmap that will be adjusted.
     * @param sensitivity The model that holds the sensitivity values for each RGB channel.
     *                   Sensitivity values should be between 0 and 1 for reduced sensitivity, and greater than 1 for increased sensitivity.
     * @return A new bitmap with adjusted color sensitivity, or `null` if the input bitmap has no configuration (e.g., it's not initialized).
     *
     * @see ColorSensitivityModel
     */
    fun adjustColorSensitivity(bitmap: Bitmap, sensitivity: ColorSensitivityModel): Bitmap? {
        val width = bitmap.width
        val height = bitmap.height
        var adjustedBitmap: Bitmap? = null

        bitmap.config?.let {
            adjustedBitmap = Bitmap.createBitmap(width, height, it)

            for (y in 0 until height) {
                for (x in 0 until width) {
                    val pixel = bitmap.getPixel(x, y)

                    var red = Color.red(pixel).toFloat()
                    var green = Color.green(pixel).toFloat()
                    var blue = Color.blue(pixel).toFloat()

                    red *= sensitivity.red
                    green *= sensitivity.green
                    blue *= sensitivity.blue

                    red = red.coerceIn(0f, 255f)
                    green = green.coerceIn(0f, 255f)
                    blue = blue.coerceIn(0f, 255f)

                    adjustedBitmap.setPixel(x, y, Color.rgb(red.toInt(), green.toInt(), blue.toInt()))
                }
            }
        }
        return adjustedBitmap
    }

}