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

object PixelPalette {

    /**
     * Cluster Color - This Defines Number of Dominant color Needed
     *
     * Downscale Factor - To Reduce the quality of bitmap image, higher number means Faster
     * output but lesser accuracy
     *
     * */
    suspend fun dominantColors(image: Bitmap, clusterColor: Int, downscaleFactor: Int = 2): List<PixelColor> {
        val downscaledImage = downscaleBitmap(image, downscaleFactor)
        val colors = extractColorsFromImage(downscaledImage)
        return kMeans(colors, clusterColor)
    }

    /**
     * Create Palette Methods has the list of Params:
     * Image - Input Image as Bitmap
     * Palette Size - This Defines Number of Palette color Needed
     * Downscale Factor - To Reduce the quality of bitmap image, higher number means Faster
     *                    output but lesser accuracy
     * Path - Path for image output or nullable will generate image to Environment folders
     * FileName - Filename for the output image or nullable will generate with random name
     * Image Width/Height - Controls the total dimension of the image
     * Color Width/Height - Controls the dimension of each color in generated palette image
     * */
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
