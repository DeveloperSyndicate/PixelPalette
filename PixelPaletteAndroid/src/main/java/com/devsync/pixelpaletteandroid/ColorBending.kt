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

import com.devsync.pixelpaletteandroid.PixelConversionRGB.RGBtoHSL
import com.devsync.pixelpaletteandroid.model.RGB
import com.devsync.pixelpaletteandroid.view.PixelConversionHSL.HSLtoRGB

/**
 * Provides various color manipulation operations such as tinting, blending, shading, and toning of colors.
 * These operations are based on additive and subtractive color models, and can be applied to RGB colors.
 */
object ColorBending {

    /**
     * Applies a tint to a given base color by adding white in the specified proportion.
     * The resulting color will be lighter based on the tintFactor value.
     *
     * @param baseColor The base color to be tinted. This is the starting color.
     * @param tintFactor The factor by which white is added to the base color.
     *                   A value between 0 and 1 where 0 means no tint (the base color),
     *                   and 1 means full white.
     * @return A new `RGB` color that is a tinted version of the base color.
     */
    fun tintColor(baseColor: RGB, tintFactor: Float): RGB {
        val newRed = (baseColor.red * (1 - tintFactor) + 255 * tintFactor).toInt().coerceIn(0, 255)
        val newGreen = (baseColor.green * (1 - tintFactor) + 255 * tintFactor).toInt().coerceIn(0, 255)
        val newBlue = (baseColor.blue * (1 - tintFactor) + 255 * tintFactor).toInt().coerceIn(0, 255)

        return RGB(newRed, newGreen, newBlue)
    }

    /**
     * Blends two colors together using additive blending. In this method, the RGB values of
     * both colors are added together, and the result is clamped to the 0-255 range.
     * This technique is typically used in light-based color systems (e.g., screens).
     *
     * @param color1 The first color to be blended.
     * @param color2 The second color to be blended.
     * @return A new `RGB` color that is the result of the additive blend of the two input colors.
     */
    fun blendColorsAdditive(color1: RGB, color2: RGB): RGB {
        val blendedRed = (color1.red + color2.red).coerceIn(0, 255)
        val blendedGreen = (color1.green + color2.green).coerceIn(0, 255)
        val blendedBlue = (color1.blue + color2.blue).coerceIn(0, 255)

        return RGB(blendedRed, blendedGreen, blendedBlue)
    }

    /**
     * Applies shading to a given base color by adding black in the specified proportion.
     * The resulting color will be darker based on the shadeFactor value.
     *
     * @param baseColor The base color to be shaded. This is the starting color.
     * @param shadeFactor The factor by which black is added to the base color.
     *                    A value between 0 and 1 where 0 means no shading (the base color),
     *                    and 1 means full black (resulting in a very dark color).
     * @return A new `RGB` color that is the shaded version of the base color.
     */
    fun shadeColor(baseColor: RGB, shadeFactor: Float): RGB {
        val black = RGB(0, 0, 0)
        val newRed = (baseColor.red * (1 - shadeFactor) + black.red * shadeFactor).toInt().coerceIn(0, 255)
        val newGreen = (baseColor.green * (1 - shadeFactor) + black.green * shadeFactor).toInt().coerceIn(0, 255)
        val newBlue = (baseColor.blue * (1 - shadeFactor) + black.blue * shadeFactor).toInt().coerceIn(0, 255)
        return RGB(newRed, newGreen, newBlue)
    }

    /**
     * Applies toning to a given base color by adding gray in the specified proportion.
     * The resulting color will be less saturated and more neutral based on the toneFactor value.
     *
     * @param baseColor The base color to be toned.
     * @param toneFactor The factor by which gray is added to the base color.
     *                   A value between 0 and 1 where 0 means no tone (the base color),
     *                   and 1 means full gray.
     * @return A new `RGB` color that is the toned version of the base color.
     */
    fun toneColor(baseColor: RGB, toneFactor: Float): RGB {
        val gray = RGB(128, 128, 128) // Simple gray color (medium gray)
        val newRed = (baseColor.red * (1 - toneFactor) + gray.red * toneFactor).toInt().coerceIn(0, 255)
        val newGreen = (baseColor.green * (1 - toneFactor) + gray.green * toneFactor).toInt().coerceIn(0, 255)
        val newBlue = (baseColor.blue * (1 - toneFactor) + gray.blue * toneFactor).toInt().coerceIn(0, 255)
        return RGB(newRed, newGreen, newBlue)
    }

    /**
     * Applies tinting to a given base color in the HSL color space by adjusting its lightness.
     * The resulting color will be lighter based on the tintFactor value.
     *
     * @param baseColor The base color to be tinted.
     * @param tintFactor The factor by which lightness is adjusted. A value between 0 and 1
     *                   where 0 means no tint (the base color), and 1 means maximum lightness.
     * @return A new `RGB` color that is the tinted version of the base color.
     */
    fun tintColorHSL(baseColor: RGB, tintFactor: Float): RGB {
        val hsl = RGBtoHSL(baseColor.red, baseColor.green, baseColor.blue)
        hsl.lightness = (hsl.lightness * (1 - tintFactor) + 1 * tintFactor).coerceIn(0f, 1f)
        return HSLtoRGB(hsl)
    }

    /**
     * Blends multiple colors together using the Screen blending mode. This mode lightens the colors
     * by inverting, multiplying, and then inverting the result.
     * The result is clamped to the 0-255 range.
     *
     * @param colors A list of colors to be blended.
     * @return A new `RGB` color that is the result of blending all the input colors using the Screen blending mode.
     */
    fun blendMultipleColorsScreen(colors: List<RGB>): RGB {
        var result = RGB(0, 0, 0)
        for (color in colors) {
            result = blendScreen(result, color)
        }
        return result
    }

    /**
     * Blends two colors together using the Screen blending mode.
     * The Screen mode lightens the colors by inverting, multiplying, and then inverting the result.
     *
     * @param color1 The first color to be blended.
     * @param color2 The second color to be blended.
     * @return A new `RGB` color that is the result of the Screen blend of the two input colors.
     */
    private fun blendScreen(color1: RGB, color2: RGB): RGB {
        val r = 255 - ((255 - color1.red) * (255 - color2.red) / 255)
        val g = 255 - ((255 - color1.green) * (255 - color2.green) / 255)
        val b = 255 - ((255 - color1.blue) * (255 - color2.blue) / 255)
        return RGB(r, g, b)
    }


    /**
     * Creates a smooth transition between two colors over a specified number of steps using a linear gradient.
     * The resulting colors will gradually blend from the start color to the end color.
     *
     * @param startColor The starting color of the gradient.
     * @param endColor The ending color of the gradient.
     * @param steps The number of steps to create in the gradient.
     * @return A list of `RGB` colors representing the linear gradient from the start color to the end color.
     */
    fun linearGradientBlend(startColor: RGB, endColor: RGB, steps: Int): List<RGB> {
        val gradient = mutableListOf<RGB>()
        for (i in 0 until steps) {
            val factor = i.toFloat() / (steps - 1)
            val blendedRed = (startColor.red * (1 - factor) + endColor.red * factor).toInt().coerceIn(0, 255)
            val blendedGreen = (startColor.green * (1 - factor) + endColor.green * factor).toInt().coerceIn(0, 255)
            val blendedBlue = (startColor.blue * (1 - factor) + endColor.blue * factor).toInt().coerceIn(0, 255)
            gradient.add(RGB(blendedRed, blendedGreen, blendedBlue))
        }
        return gradient
    }


    /**
     * Shifts the hue of a given color by a specified amount. This creates a new color variation
     * by adjusting the hue value in the HSL color space, keeping the saturation and lightness unchanged.
     *
     * @param baseColor The base color to be shifted.
     * @param hueShift The amount by which the hue should be shifted. This value is typically in degrees
     *                 (0 - 360), and can be positive or negative.
     * @return A new `RGB` color that is the result of the hue shift.
     */
    fun shiftHue(baseColor: RGB, hueShift: Float): RGB {
        val hsl = RGBtoHSL(baseColor.red, baseColor.green, baseColor.blue)
        hsl.hue = (hsl.hue + hueShift) % 360f
        return HSLtoRGB(hsl)
    }


    /**
     * Blends two colors together using the Soft Light blending mode. This mode creates a subtle contrast
     * effect, where the second color lightens or darkens the first color based on the brightness of the second.
     *
     * @param color1 The first color to be blended.
     * @param color2 The second color to be blended.
     * @return A new `RGB` color that is the result of the Soft Light blend of the two input colors.
     */
    fun blendSoftLight(color1: RGB, color2: RGB): RGB {
        val r = if (color2.red < 128) {
            (2 * color2.red * color1.red / 255).toInt()
        } else {
            (255 - 2 * (255 - color2.red) * (255 - color1.red) / 255).toInt()
        }
        val g = if (color2.green < 128) {
            (2 * color2.green * color1.green / 255).toInt()
        } else {
            (255 - 2 * (255 - color2.green) * (255 - color1.green) / 255).toInt()
        }
        val b = if (color2.blue < 128) {
            (2 * color2.blue * color1.blue / 255).toInt()
        } else {
            (255 - 2 * (255 - color2.blue) * (255 - color1.blue) / 255).toInt()
        }
        return RGB(r, g, b)
    }

}