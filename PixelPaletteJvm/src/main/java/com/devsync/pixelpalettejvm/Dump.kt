package com.devsync.pixelpalettejvm

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO


fun main() {
    val r = 80
    val b = 80
    val g = 215
    println(PixelConversionRGB.RGBtoHex(r, g, b))
    println(PixelConversionRGB.RGBtoHSB(r, g, b))
    println(PixelConversionRGB.RGBtoHSL(r, g, b))
    println(PixelConversionRGB.RGBtoCMYK(r, g, b))
    println(PixelConversionRGB.RGBtoCIELCH(r, g, b))

    val imageFile = File("D:\\image.jpg")
    val image: BufferedImage = ImageIO.read(imageFile)

    PixelPalette.createPalette(image, 5, "D:\\palette.png")
}