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
package com.devsync.pixelpalettejvm

import com.devsync.pixelpalettejvm.model.ColorSensitivityModel
import java.awt.Color
import java.awt.image.BufferedImage

/**
 * The `ColorSensitivity` object provides functions for adjusting the color sensitivity of an image.
 * It allows modification of the Red, Green, and Blue (RGB) channels of an image based on the provided
 * sensitivity values for each color channel. The sensitivity values determine how much influence each
 * color channel (Red, Green, Blue) has during the adjustment process.
 *
 * The adjustment is achieved by multiplying each RGB channel by its corresponding sensitivity factor.
 * The resulting RGB values are then clamped between 0 and 255 to ensure they remain within the valid color range.
 */
object ColorSensitivity {

    /**
     * Adjusts the color sensitivity of an image based on the provided sensitivity factors for each RGB channel.
     *
     * This method modifies the color channels (Red, Green, and Blue) of the input image according to the
     * sensitivity values provided in the `ColorSensitivityModel`. Each color channel (Red, Green, Blue) will be
     * scaled by its corresponding sensitivity factor. The final RGB values are clamped between 0 and 255
     * to ensure valid color values for the resulting image.
     *
     * @param image The input image that will be adjusted.
     * @param sensitivity The model that holds the sensitivity values for each RGB channel.
     *                   Sensitivity values should be between 0 and 1 for reduced sensitivity, and greater than 1 for increased sensitivity.
     * @return A new image with adjusted color sensitivity.
     */
    fun adjustColorSensitivity(image: BufferedImage, sensitivity: ColorSensitivityModel): BufferedImage {
        val width = image.width
        val height = image.height
        val adjustedImage = BufferedImage(width, height, image.type)

        for (y in 0 until height) {
            for (x in 0 until width) {
                val pixel = image.getRGB(x, y)

                val red = Color(pixel).red.toFloat()
                val green = Color(pixel).green.toFloat()
                val blue = Color(pixel).blue.toFloat()

                var adjustedRed = red * sensitivity.red
                var adjustedGreen = green * sensitivity.green
                var adjustedBlue = blue * sensitivity.blue

                adjustedRed = adjustedRed.coerceIn(0f, 255f)
                adjustedGreen = adjustedGreen.coerceIn(0f, 255f)
                adjustedBlue = adjustedBlue.coerceIn(0f, 255f)

                val newColor = Color(adjustedRed.toInt(), adjustedGreen.toInt(), adjustedBlue.toInt())
                adjustedImage.setRGB(x, y, newColor.rgb)
            }
        }

        return adjustedImage
    }
}