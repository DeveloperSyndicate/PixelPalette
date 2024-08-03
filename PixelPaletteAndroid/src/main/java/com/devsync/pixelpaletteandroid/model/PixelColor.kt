package com.devsync.pixelpaletteandroid.model

import kotlin.math.sqrt

data class PixelColor(val red: Int, val green: Int, val blue: Int, val alpha: Int) {
    constructor(colorInt: Int) : this(
        (colorInt shr 16) and 0xFF,
        (colorInt shr 8) and 0xFF,
        colorInt and 0xFF,
        (colorInt shr 24) and 0xFF
    )

    fun toInt(): Int {
        return (alpha shl 24) or (red shl 16) or (green shl 8) or blue
    }

    fun distanceTo(other: PixelColor): Double {
        val redDiff = red - other.red
        val greenDiff = green - other.green
        val blueDiff = blue - other.blue
        return sqrt((redDiff * redDiff + greenDiff * greenDiff + blueDiff * blueDiff).toDouble())
    }
}
