package com.devsync.pixelpaletteandroid.utils

import com.devsync.pixelpaletteandroid.model.LAB
import com.devsync.pixelpaletteandroid.model.XYZ
import kotlin.math.pow
import kotlin.math.sqrt

//Pantone Conversion Util
internal fun labDistance(lab1: Triple<Double, Double, Double>, lab2: Triple<Double, Double, Double>): Double {
    val (l1, a1, b1) = lab1
    val (l2, a2, b2) = lab2
    return sqrt((l1 - l2).pow(2) + (a1 - a2).pow(2) + (b1 - b2).pow(2))
}

internal fun rgbToLab(r: Int, g: Int, b: Int): Triple<Double, Double, Double> {
    val rNorm = r / 255.0
    val gNorm = g / 255.0
    val bNorm = b / 255.0

    fun gammaCorrect(c: Double): Double {
        return if (c > 0.04045) {
            Math.pow((c + 0.055) / 1.055, 2.4)
        } else {
            c / 12.92
        }
    }

    val rLinear = gammaCorrect(rNorm)
    val gLinear = gammaCorrect(gNorm)
    val bLinear = gammaCorrect(bNorm)

    val x = rLinear * 0.4124564 + gLinear * 0.3575761 + bLinear * 0.1804375
    val y = rLinear * 0.2126729 + gLinear * 0.7151522 + bLinear * 0.0721750
    val z = rLinear * 0.0193339 + gLinear * 0.1191920 + bLinear * 0.9503041

    val xNorm = x / 95.047
    val yNorm = y / 100.000
    val zNorm = z / 108.883

    fun f(t: Double): Double {
        return if (t > 0.008856) {
            t.pow(1 / 3.0)
        } else {
            (t * 7.787) + (16.0 / 116.0)
        }
    }

    val l = (116 * f(yNorm)) - 16
    val a = 500 * (f(xNorm) - f(yNorm))
    val b = 200 * (f(yNorm) - f(zNorm))

    return Triple(l, a, b)
}

//Pantone Conversion Util


//RAL Conversion Util

internal fun labDistance(lab1: LAB, lab2: LAB): Double {
    return sqrt(
        (lab2.lightness - lab1.lightness).pow(2.0) +
                (lab2.a - lab1.a).pow(2.0) +
                (lab2.b - lab1.b).pow(2.0)
    )
}

//xyz to Lab

fun xyzToLab(xyz: XYZ): LAB {
    val xRef = 95.047
    val yRef = 100.000
    val zRef = 108.883

    val xNorm = xyz.x / xRef
    val yNorm = xyz.y / yRef
    val zNorm = xyz.z / zRef

    fun f(t: Double): Double {
        return if (t > 0.008856) {
            t.pow(1.0 / 3.0)
        } else {
            (t * 7.787) + (16.0 / 116.0)
        }
    }

    val l = (116 * f(yNorm)) - 16
    val a = 500 * (f(xNorm) - f(yNorm))
    val b = 200 * (f(yNorm) - f(zNorm))

    return LAB(l, a, b)
}
