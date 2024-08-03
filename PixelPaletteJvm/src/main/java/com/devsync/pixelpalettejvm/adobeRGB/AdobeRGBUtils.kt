package com.devsync.pixelpalettejvm.adobeRGB

import com.devsync.pixelpalettejvm.model.XYZ
import kotlin.math.pow

fun gammaCorrectAdobeRGB(c: Double): Double {
    return if (c <= 0.04045) {
        c / 12.92
    } else {
        ((c + 0.055) / 1.055).pow(2.4)
    }
}