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

import com.devsync.pixelpalettejvm.model.PaletteModel
import java.awt.Color
import java.awt.Font
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

object PixelPaletteUtil {
    private fun getContrastingColor(color: Color): Color {
        val brightness = (0.299 * color.red + 0.587 * color.green + 0.114 * color.blue).toInt()
        return if (brightness > 186) Color.BLACK else Color.WHITE
    }

    internal fun displayPalette(palette: List<Color>, colorWidth: Int, colorHeight: Int, imageWidth: Int, imageHeight: Int, path: String, isVertical: Boolean): PaletteModel {
        val paletteImage = BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB)
        val graphics = paletteImage.createGraphics()

        graphics.font = Font("Arial", Font.PLAIN, 12)

        if (isVertical) {
            val numRows = imageHeight / colorHeight

            for (i in palette.indices) {
                if (i >= numRows) break

                val color = palette[i]

                val x = (imageWidth - colorWidth) / 2
                val y = i * colorHeight

                graphics.color = color
                graphics.fillRect(x, y, colorWidth, colorHeight)

                val textColor = getContrastingColor(color)
                graphics.color = textColor

                val hexCode = String.format("#%02X%02X%02X", color.red, color.green, color.blue)
                graphics.drawString(hexCode, x + 5, y + colorHeight - 10)
            }
        } else {
            val numColumns = imageWidth / colorWidth

            for (i in palette.indices) {
                if (i >= numColumns) break

                val color = palette[i]

                val x = i * colorWidth
                val y = (imageHeight - colorHeight) / 2

                graphics.color = color
                graphics.fillRect(x, y, colorWidth, colorHeight)

                val textColor = getContrastingColor(color)
                graphics.color = textColor

                val hexCode = String.format("#%02X%02X%02X", color.red, color.green, color.blue)
                graphics.drawString(hexCode, x + 5, y + colorHeight - 10)
            }
        }

        graphics.dispose()

        val outputFile = File(path)
        ImageIO.write(paletteImage, "PNG", outputFile)

        println("Palette saved to ${outputFile.absolutePath}")
        return PaletteModel(palette, paletteImage)
    }
}