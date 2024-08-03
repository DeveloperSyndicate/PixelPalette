package com.devsync.pixelpaletteandroid

import android.graphics.Bitmap
import com.devsync.pixelpaletteandroid.model.PaletteModel
import com.devsync.pixelpaletteandroid.model.PixelColor
import com.devsync.pixelpaletteandroid.utils.ColorLab.extractColorsFromImage
import com.devsync.pixelpaletteandroid.utils.ColorLab.kMeans
import com.devsync.pixelpaletteandroid.utils.PixelPaletteUtil.displayPalette
import com.devsync.pixelpaletteandroid.utils.PixelPaletteUtil.downscaleBitmap

object PixelPalette {

    suspend fun dominantColors(image: Bitmap, k: Int, downscaleFactor: Int = 2): List<PixelColor> {
        val downscaledImage = downscaleBitmap(image, downscaleFactor)
        val colors = extractColorsFromImage(downscaledImage)
        return kMeans(colors, k)
    }

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
