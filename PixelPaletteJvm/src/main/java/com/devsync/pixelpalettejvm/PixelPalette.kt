package com.devsync.pixelpalettejvm

import com.devsync.pixelpalettejvm.model.PaletteModel
import java.awt.Color
import java.awt.image.BufferedImage
import com.devsync.pixelpalettejvm.utils.ColorLab.extractColorsFromImage
import com.devsync.pixelpalettejvm.utils.ColorLab.kMeans
import com.devsync.pixelpalettejvm.utils.PixelPaletteUtil.displayPalette


object PixelPalette {

    fun dominantColors(image: BufferedImage, k: Int): List<Color> {
        val colors = extractColorsFromImage(image)
        return kMeans(colors, k)
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