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
import com.devsync.pixelpaletteandroid.model.PaletteModel
import com.devsync.pixelpaletteandroid.model.PixelColor
import com.devsync.pixelpaletteandroid.utils.ColorLab.extractColorsFromImage
import com.devsync.pixelpaletteandroid.utils.ColorLab.kMeans
import com.devsync.pixelpaletteandroid.utils.PixelPaletteUtil.displayPalette
import com.devsync.pixelpaletteandroid.utils.PixelPaletteUtil.downscaleBitmap

/**
 * The PixelPalette object provides methods to analyze and extract dominant colors from an image
 * using K-means clustering. It allows for downscaling the image to improve performance, followed
 * by the identification of the most prominent colors. Additionally, the object can generate a color
 * palette image based on the extracted dominant colors.
 *
 * The two primary functions of this object are:
 * 1. `dominantColors`: Extracts the most dominant colors from the provided image.
 * 2. `createPalette`: Generates a color palette from the image and optionally saves the palette image.
 *
 * The K-means clustering algorithm is used in both functions to identify the most representative
 * colors from the image data. The image is downscaled before processing to improve performance,
 * with the option to adjust the level of downscaling.
 *
 * @see dominantColors
 * @see createPalette
 */
object PixelPalette {

    /**
     * Extracts the dominant colors from the provided image using K-means clustering.
     * The image is downscaled before color extraction to improve performance,
     * and then K-means clustering is applied to identify the most dominant colors.
     *
     * @param image The input image from which the dominant colors will be extracted.
     * @param clusterColor The desired number of dominant colors to be identified.
     * @param downscaleFactor The factor by which to downscale the original image before extracting colors. Default is 2.
     * @return A list of [PixelColor] objects representing the most dominant colors from the image.
     */
    suspend fun dominantColors(image: Bitmap, clusterColor: Int, downscaleFactor: Int = 2): List<PixelColor> {
        val downscaledImage = downscaleBitmap(image, downscaleFactor)
        val colors = extractColorsFromImage(downscaledImage)
        return kMeans(colors, clusterColor)
    }


    /**
     * Creates a color palette from the provided image using K-means clustering.
     * The function downscales the image, extracts the most dominant colors,
     * performs K-means clustering to generate the color palette, and then
     * displays the palette as an image.
     *
     * @param image The input image from which the palette will be created.
     * @param paletteSize The desired number of colors in the resulting palette.
     * @param path Optional path where the generated palette image will be saved.
     * @param fileName Optional name for the palette image file.
     * @param imageWidth The width of the downscaled image to be used for extracting colors. Default is 600.
     * @param imageHeight The height of the downscaled image to be used for extracting colors. Default is 800.
     * @param colorWidth The width of the generated color palette image. Default is 600.
     * @param colorHeight The height of the generated color palette image. Default is 100.
     * @param downscaleFactor The factor by which to downscale the original image before extracting colors. Default is 1 (no downscaling).
     * @return A [PaletteModel] representing the color palette generated from the image.
     */
    suspend fun createPalette(
        image: Bitmap,
        paletteSize: Int,
        path: String? = null,
        fileName: String? = null,
        imageWidth: Int = 600,
        imageHeight: Int = 800,
        colorWidth: Int = 600,
        colorHeight: Int = 100,
        downscaleFactor: Int = 1,
    ) : PaletteModel {
        val downscaledImage = downscaleBitmap(image, downscaleFactor)
        val colors = extractColorsFromImage(downscaledImage)
        val palette = kMeans(colors, paletteSize)
        return displayPalette(palette, colorWidth, colorHeight, imageWidth, imageHeight, path,fileName, true)
    }

}
