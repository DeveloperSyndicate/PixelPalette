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

package com.devsync.pixelpalettejvm

import com.devsync.pixelpalettejvm.model.PaletteModel
import java.awt.Color
import java.awt.image.BufferedImage
import com.devsync.pixelpalettejvm.utils.ColorLab.extractColorsFromImage
import com.devsync.pixelpalettejvm.utils.ColorLab.kMeans
import com.devsync.pixelpalettejvm.utils.PixelPaletteUtil.displayPalette


object PixelPalette {

    fun dominantColors(image: BufferedImage, clusterCount: Int): List<Color> {
        val colors = extractColorsFromImage(image)
        return kMeans(colors, clusterCount)
    }

    fun createPalette(
        image: BufferedImage,
        paletteSize: Int,
        path: String,
        imageWidth: Int = 600,
        imageHeight: Int = 800,
        colorWidth: Int = 600,
        colorHeight: Int = 100,
    ): PaletteModel {
        val colors = extractColorsFromImage(image)
        val palette = kMeans(colors, paletteSize)
        return displayPalette(palette, colorWidth, colorHeight, imageWidth, imageHeight, path, true)
    }


}