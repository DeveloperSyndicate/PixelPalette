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

import java.awt.Color
import java.awt.image.BufferedImage
import com.devsync.pixelpalettejvm.PixelConversionRGB.RGBtoHSL
import com.devsync.pixelpalettejvm.utils.PixelConversionHSL.HSLtoRGB

object ColorAdjustment {

    /**
     * Adjusts the color balance by scaling the Red, Green, and Blue channels.
     *
     * @param image The original image to be color balanced.
     * @param redFactor Factor by which to scale the red channel (1.0 means no change).
     * @param greenFactor Factor by which to scale the green channel (1.0 means no change).
     * @param blueFactor Factor by which to scale the blue channel (1.0 means no change).
     * @return A new image with the adjusted color balance.
     */
    fun adjustColorBalance(
        image: BufferedImage,
        redFactor: Float,
        greenFactor: Float,
        blueFactor: Float
    ): BufferedImage {
        val width = image.width
        val height = image.height
        val adjustedImage = BufferedImage(width, height, image.type)

        for (y in 0 until height) {
            for (x in 0 until width) {
                val pixel = image.getRGB(x, y)
                val color = Color(pixel)

                val red = color.red
                val green = color.green
                val blue = color.blue

                val newRed = (red * redFactor).toInt().coerceIn(0, 255)
                val newGreen = (green * greenFactor).toInt().coerceIn(0, 255)
                val newBlue = (blue * blueFactor).toInt().coerceIn(0, 255)

                adjustedImage.setRGB(x, y, Color(newRed, newGreen, newBlue).rgb)
            }
        }

        return adjustedImage
    }

    /**
     * Adjusts the brightness of an image.
     *
     * @param image The original image to be adjusted.
     * @param brightnessFactor Factor by which to adjust the brightness. (1.0 means no change, greater than 1 increases brightness).
     * @return A new image with the adjusted brightness.
     */
    fun adjustBrightness(image: BufferedImage, brightnessFactor: Float): BufferedImage {
        val width = image.width
        val height = image.height
        val adjustedImage = BufferedImage(width, height, image.type)

        for (y in 0 until height) {
            for (x in 0 until width) {
                val pixel = image.getRGB(x, y)
                val color = Color(pixel)

                val red = color.red
                val green = color.green
                val blue = color.blue

                val newRed = (red * brightnessFactor).toInt().coerceIn(0, 255)
                val newGreen = (green * brightnessFactor).toInt().coerceIn(0, 255)
                val newBlue = (blue * brightnessFactor).toInt().coerceIn(0, 255)

                adjustedImage.setRGB(x, y, Color(newRed, newGreen, newBlue).rgb)
            }
        }

        return adjustedImage
    }

    /**
     * Adjusts the contrast of an image.
     *
     * @param image The original image to be adjusted.
     * @param contrastFactor Factor by which to adjust the contrast. (1.0 means no change, greater than 1 increases contrast).
     * @return A new image with the adjusted contrast.
     */
    fun adjustContrast(image: BufferedImage, contrastFactor: Float): BufferedImage {
        val width = image.width
        val height = image.height
        val adjustedImage = BufferedImage(width, height, image.type)

        for (y in 0 until height) {
            for (x in 0 until width) {
                val pixel = image.getRGB(x, y)
                val color = Color(pixel)

                val red = color.red
                val green = color.green
                val blue = color.blue

                val newRed = ((red - 128) * contrastFactor + 128).toInt().coerceIn(0, 255)
                val newGreen = ((green - 128) * contrastFactor + 128).toInt().coerceIn(0, 255)
                val newBlue = ((blue - 128) * contrastFactor + 128).toInt().coerceIn(0, 255)

                adjustedImage.setRGB(x, y, Color(newRed, newGreen, newBlue).rgb)
            }
        }

        return adjustedImage
    }
}
