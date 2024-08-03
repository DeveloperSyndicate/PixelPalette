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

package com.devsync.pixelpaletteandroid

import com.devsync.pixelpaletteandroid.adobeRGB.gammaCorrectAdobeRGB
import com.devsync.pixelpaletteandroid.cielch.labToLch
import com.devsync.pixelpaletteandroid.model.CIELCH
import com.devsync.pixelpaletteandroid.model.CIELUV
import com.devsync.pixelpaletteandroid.model.CMYK
import com.devsync.pixelpaletteandroid.model.HSB
import com.devsync.pixelpaletteandroid.model.HSL
import com.devsync.pixelpaletteandroid.model.HWB
import com.devsync.pixelpaletteandroid.model.LAB
import com.devsync.pixelpaletteandroid.model.Munsell
import com.devsync.pixelpaletteandroid.model.MunsellColor
import com.devsync.pixelpaletteandroid.model.Pantone
import com.devsync.pixelpaletteandroid.model.RAL
import com.devsync.pixelpaletteandroid.model.Rec2020
import com.devsync.pixelpaletteandroid.model.SRGB
import com.devsync.pixelpaletteandroid.model.XYZ
import com.devsync.pixelpaletteandroid.munsell.labToMunsell
import com.devsync.pixelpaletteandroid.sRGB.linearRgbToSRGB
import com.devsync.pixelpaletteandroid.sRGB.rgbToLinearRgb
import com.devsync.pixelpaletteandroid.utils.labDistance
import com.devsync.pixelpaletteandroid.utils.rgbToLab
import com.devsync.pixelpaletteandroid.utils.xyzToLab
import kotlin.math.cbrt
import kotlin.math.pow

object PixelConversionRGB {
    //HEX
    fun RGBtoHex(red: Int, green: Int, blue: Int): String {
        return String.format("#%02X%02X%02X", red, green, blue)
    }

    //CMYK
    fun RGBtoCMYK(red: Int, green: Int, blue: Int): CMYK {
        val rNorm = red / 255.0
        val gNorm = green / 255.0
        val bNorm = blue / 255.0

        val k = 1.0 - maxOf(rNorm, gNorm, bNorm)
        val c = if (k < 1) (1.0 - rNorm - k) / (1.0 - k) else 0.0
        val m = if (k < 1) (1.0 - gNorm - k) / (1.0 - k) else 0.0
        val y = if (k < 1) (1.0 - bNorm - k) / (1.0 - k) else 0.0

        return CMYK(c, m, y, k)
    }

    //HSB
    fun RGBtoHSB(red: Int, green: Int, blue: Int): HSB {
        val rNorm = red / 255.0
        val gNorm = green / 255.0
        val bNorm = blue / 255.0

        val cMax = maxOf(rNorm, gNorm, bNorm)
        val cMin = minOf(rNorm, gNorm, bNorm)
        val delta = cMax - cMin

        val hue = when {
            delta == 0.0 -> 0f
            cMax == rNorm -> 60 * (((gNorm - bNorm) / delta) % 6)
            cMax == gNorm -> 60 * (((bNorm - rNorm) / delta) + 2)
            else -> 60 * (((rNorm - gNorm) / delta) + 4)
        }.toFloat()

        val saturation = if (cMax == 0.0) 0f else (delta / cMax).toFloat()
        val brightness = cMax.toFloat()

        return HSB(hue, saturation, brightness)
    }

    //HSL
    fun RGBtoHSL(red: Int, green: Int, blue: Int): HSL {
        val rNorm = red / 255.0
        val gNorm = green / 255.0
        val bNorm = blue / 255.0

        val cMax = maxOf(rNorm, gNorm, bNorm)
        val cMin = minOf(rNorm, gNorm, bNorm)
        val delta = cMax - cMin

        val hue = when {
            delta == 0.0 -> 0f
            cMax == rNorm -> 60 * (((gNorm - bNorm) / delta) % 6)
            cMax == gNorm -> 60 * (((bNorm - rNorm) / delta) + 2)
            else -> 60 * (((rNorm - gNorm) / delta) + 4)
        }.toFloat()

        val lightness = (cMax + cMin) / 2.0
        val saturation = if (delta == 0.0) 0.0 else delta / (1.0 - Math.abs(2 * lightness - 1.0))

        return HSL(hue, saturation.toFloat(), lightness.toFloat())
    }

    //XYZ
    fun RGBtoXYZ(red: Int, green: Int, blue: Int, scale: Int = 100): XYZ {
        val rNorm = red / 255.0
        val gNorm = green / 255.0
        val bNorm = blue / 255.0

        val rLinear = if (rNorm > 0.04045) ((rNorm + 0.055) / 1.055).pow(2.4) else rNorm / 12.92
        val gLinear = if (gNorm > 0.04045) ((gNorm + 0.055) / 1.055).pow(2.4) else gNorm / 12.92
        val bLinear = if (bNorm > 0.04045) ((bNorm + 0.055) / 1.055).pow(2.4) else bNorm / 12.92

        val x = rLinear * 0.4124 + gLinear * 0.3576 + bLinear * 0.1805
        val y = rLinear * 0.2126 + gLinear * 0.7152 + bLinear * 0.0722
        val z = rLinear * 0.0193 + gLinear * 0.1192 + bLinear * 0.9505

        return XYZ(x * scale, y * scale, z * scale)
    }

    //LAB
    fun RGBtoLAB(r: Int, g: Int, b: Int): LAB {
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

        return LAB(l, a, b)
    }

    //Pantone Colors
    fun RGBToPantone(red: Int, green: Int, blue: Int, pantones: List<Pantone>): Pantone {
        val targetLab = rgbToLab(red, green, blue)
        return pantones.minBy { pantoneColor ->
            val pantoneLab = rgbToLab(pantoneColor.red, pantoneColor.green, pantoneColor.blue)
            labDistance(targetLab, pantoneLab)
        }
    }

    //RAL Colors
    fun RGBtoRAL(red: Int, green: Int, blue: Int, ral: List<RAL>): RAL {
        val inputLab = rgbToLab(red, green, blue)

        return ral.minBy {
            val ralLab = rgbToLab(it.red, it.green, it.blue)
            labDistance(inputLab, ralLab)
        }
    }

    //Munsell
    fun RGBtoMunsell(
        red: Int,
        green: Int,
        blue: Int,
        munsellDatabase: List<MunsellColor>,
    ): Munsell {
        val xyz = RGBtoXYZ(red, green, blue)
        val lab = xyzToLab(xyz)
        return labToMunsell(lab, munsellDatabase)
    }

    //HWB
    fun RGBtoHWB(r: Int, g: Int, b: Int): HWB {
        val rNorm = r / 255.0
        val gNorm = g / 255.0
        val bNorm = b / 255.0

        val cMax = maxOf(rNorm, gNorm, bNorm)
        val cMin = minOf(rNorm, gNorm, bNorm)
        val delta = cMax - cMin

        val hue = when {
            delta == 0.0 -> 0f
            cMax == rNorm -> (60 * ((gNorm - bNorm) / delta) + 360) % 360
            cMax == gNorm -> (60 * ((bNorm - rNorm) / delta) + 120) % 360
            else -> (60 * ((rNorm - gNorm) / delta) + 240) % 360
        }.toFloat()

        val white = cMin.toFloat()
        val black = (1 - cMax).toFloat()

        return HWB(hue, white, black)
    }

    //sRGB
    fun rgbToSRgb(r: Int, g: Int, b: Int): SRGB {
        val (rLinear, gLinear, bLinear) = rgbToLinearRgb(r, g, b)
        val (rFinal, gFinal, bFinal) = linearRgbToSRGB(rLinear, gLinear, bLinear)
        return SRGB(rFinal, gFinal, bFinal)
    }

    //AdobeRGB
    fun RGBToAdobeRGB(r: Int, g: Int, b: Int): XYZ {
        val rNorm = r / 255.0
        val gNorm = g / 255.0
        val bNorm = b / 255.0

        val rLinear = gammaCorrectAdobeRGB(rNorm)
        val gLinear = gammaCorrectAdobeRGB(gNorm)
        val bLinear = gammaCorrectAdobeRGB(bNorm)

        val x = rLinear * 0.5767309 + gLinear * 0.1855540 + bLinear * 0.1881852
        val y = rLinear * 0.2973769 + gLinear * 0.6273491 + bLinear * 0.0752741
        val z = rLinear * 0.0270343 + gLinear * 0.0706872 + bLinear * 0.9911085

        return XYZ(x, y, z)
    }

    //Rec2020
    fun rgbToRec2020(r: Int, g: Int, b: Int): Rec2020 {
        val rNorm = r / 255.0
        val gNorm = g / 255.0
        val bNorm = b / 255.0
        fun gammaCorrectInverse(c: Double): Double {
            return if (c < 0.0181) {
                c / 4.5
            } else {
                ((c + 0.0993) / 1.0993).pow(1.0 / 0.45)
            }
        }

        val rLinear = gammaCorrectInverse(rNorm)
        val gLinear = gammaCorrectInverse(gNorm)
        val bLinear = gammaCorrectInverse(bNorm)

        val x =
            rLinear * 0.6369580483012914 + gLinear * 0.14461690358620832 + bLinear * 0.1688809751641721
        val y =
            rLinear * 0.2627002120112671 + gLinear * 0.6779980715188708 + bLinear * 0.05930171646986196
        val z =
            rLinear * 0.0000000000000000 + gLinear * 0.028072693049087428 + bLinear * 1.060985057710791

        return Rec2020(x, y, z)
    }

    //CIELUV
    fun RGBtoCIELUV(r: Int, g: Int, b: Int): CIELUV {
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

        val refX = 95.047
        val refY = 100.000
        val refZ = 108.883

        val xNorm = x / refX
        val yNorm = y / refY
        val zNorm = z / refZ

        val yr = yNorm
        val l = if (yr > 0.008856) {
            116.0 * cbrt(yr) - 16
        } else {
            903.3 * yr
        }

        val uPrime = (4 * x) / (x + 15 * y + 3 * z)
        val vPrime = (9 * y) / (x + 15 * y + 3 * z)
        val uPrimeRef = (4 * refX) / (refX + 15 * refY + 3 * refZ)
        val vPrimeRef = (9 * refY) / (refX + 15 * refY + 3 * refZ)

        val u = 13 * l * (uPrime - uPrimeRef)
        val v = 13 * l * (vPrime - vPrimeRef)

        return CIELUV(l, u, v)
    }

    //CIELCH
    fun RGBtoCIELCH(r: Int, g: Int, b: Int): CIELCH {
        val lab = com.devsync.pixelpaletteandroid.cielch.rgbToLab(r, g, b)
        return labToLch(lab)
    }



}