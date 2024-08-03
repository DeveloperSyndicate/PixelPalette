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

package com.devsync.pixelpalettejvm.sRGB

fun linearize(value: Double): Double {
    return if (value <= 0.04045) {
        value / 12.92
    } else {
        Math.pow((value + 0.055) / 1.055, 2.4)
    }
}

fun gammaCorrect(value: Double): Double {
    return if (value <= 0.0031308) {
        value * 12.92
    } else {
        1.055 * Math.pow(value, 1.0 / 2.4) - 0.055
    }
}

fun rgbToLinearRgb(r: Int, g: Int, b: Int): Triple<Double, Double, Double> {
    val rNorm = r / 255.0
    val gNorm = g / 255.0
    val bNorm = b / 255.0

    val rLinear = linearize(rNorm)
    val gLinear = linearize(gNorm)
    val bLinear = linearize(bNorm)

    return Triple(rLinear, gLinear, bLinear)
}
fun linearRgbToSrgb(rLinear: Double, gLinear: Double, bLinear: Double): Triple<Int, Int, Int> {
    val rGammaCorrected = gammaCorrect(rLinear)
    val gGammaCorrected = gammaCorrect(gLinear)
    val bGammaCorrected = gammaCorrect(bLinear)

    val rFinal = (rGammaCorrected * 255.0).toInt().coerceIn(0, 255)
    val gFinal = (gGammaCorrected * 255.0).toInt().coerceIn(0, 255)
    val bFinal = (bGammaCorrected * 255.0).toInt().coerceIn(0, 255)

    return Triple(rFinal, gFinal, bFinal)
}
