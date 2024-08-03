package com.devsync.pixelpaletteandroid.sRGB

private fun linearize(value: Double): Double {
    return if (value <= 0.04045) {
        value / 12.92
    } else {
        Math.pow((value + 0.055) / 1.055, 2.4)
    }
}

private fun gammaCorrect(value: Double): Double {
    return if (value <= 0.0031308) {
        value * 12.92
    } else {
        1.055 * Math.pow(value, 1.0 / 2.4) - 0.055
    }
}

internal fun rgbToLinearRgb(r: Int, g: Int, b: Int): Triple<Double, Double, Double> {
    val rNorm = r / 255.0
    val gNorm = g / 255.0
    val bNorm = b / 255.0

    val rLinear = linearize(rNorm)
    val gLinear = linearize(gNorm)
    val bLinear = linearize(bNorm)

    return Triple(rLinear, gLinear, bLinear)
}
internal fun linearRgbToSRGB(rLinear: Double, gLinear: Double, bLinear: Double): Triple<Int, Int, Int> {
    val rGammaCorrected = gammaCorrect(rLinear)
    val gGammaCorrected = gammaCorrect(gLinear)
    val bGammaCorrected = gammaCorrect(bLinear)

    val rFinal = (rGammaCorrected * 255.0).toInt().coerceIn(0, 255)
    val gFinal = (gGammaCorrected * 255.0).toInt().coerceIn(0, 255)
    val bFinal = (bGammaCorrected * 255.0).toInt().coerceIn(0, 255)

    return Triple(rFinal, gFinal, bFinal)
}
