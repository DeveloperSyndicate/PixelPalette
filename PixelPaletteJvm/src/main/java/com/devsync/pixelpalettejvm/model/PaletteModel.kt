package com.devsync.pixelpalettejvm.model

import java.awt.Color
import java.awt.image.BufferedImage


data class PaletteModel(val palette: List<Color>, val paletteImage: BufferedImage?)
