package com.devsync.pixelpalettejvm.cielch

import com.devsync.pixelpalettejvm.model.CIELAB
import com.devsync.pixelpalettejvm.model.CIELCH
import kotlin.math.atan2
import kotlin.math.pow
import kotlin.math.sqrt

fun rgbToLab(r: Int, g: Int, b: Int): CIELAB {
    val rNorm = r / 255.0
    val gNorm = g / 255.0
    val bNorm = b / 255.0

    fun gammaCorrect(c: Double): Double {
        return if (c > 0.04045) {
            ((c + 0.055) / 1.055).pow(2.4)
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

    return CIELAB(l, a, b)
}

fun labToLch(lab: CIELAB): CIELCH {
    val c = sqrt(lab.a * lab.a + lab.b * lab.b)
    val h = Math.toDegrees(atan2(lab.b, lab.a))
    val hue = if (h < 0) h + 360 else h
    return CIELCH(lab.lightness, c, hue)
}