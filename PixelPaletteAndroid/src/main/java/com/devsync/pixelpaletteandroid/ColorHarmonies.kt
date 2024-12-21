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
 * Date: 11-12-2024
 */

package com.devsync.pixelpaletteandroid

import com.devsync.pixelpaletteandroid.model.RGB

/**
 * A utility object that provides methods to generate various color schemes based on a base color.
 * These color schemes are designed to help create harmonious color palettes for use in design,
 * UI development, and other color-related tasks.
 *
 * The following color schemes are supported:
 * - **Monochromatic**: Shades and tints of the base color.
 * - **Analogous**: Colors adjacent to the base color on the color wheel.
 * - **Complementary**: The base color and its complementary color (opposite on the color wheel).
 * - **Triadic**: Three colors evenly spaced around the color wheel.
 * - **Tetradic**: Two complementary color pairs.
 * - **Square**: Four colors evenly spaced around the color wheel.
 *
 * Each method generates a list of `RGB` colors representing the corresponding color scheme.
 */
object ColorHarmonies {

    /**
     * Generates a monochromatic color scheme based on a base color.
     * The scheme consists of various shades and tints of the base color,
     * where the shades are created by adjusting the lightness of the base color.
     *
     * @param baseColor The base color to generate the monochromatic scheme from.
     * @param numberOfShades The number of shades to generate. The higher the value,
     *                       the more shades of the base color will be created.
     * @return A list of `RGB` colors representing the monochromatic color scheme.
     */
    fun monochromaticScheme(baseColor: RGB, numberOfShades: Int): List<RGB> {
        val shades = mutableListOf<RGB>()
        for (i in 0 until numberOfShades) {
            val factor = i.toFloat() / (numberOfShades - 1)
            val newR = (baseColor.red * (1 - factor)).toInt().coerceIn(0, 255)
            val newG = (baseColor.green * (1 - factor)).toInt().coerceIn(0, 255)
            val newB = (baseColor.blue * (1 - factor)).toInt().coerceIn(0, 255)
            shades.add(RGB(newR, newG, newB))
        }
        return shades
    }

    /**
     * Generates an analogous color scheme based on a base color.
     * The analogous scheme consists of colors that are next to each other on the color wheel,
     * with the base color being the center of the scheme.
     *
     * @param baseColor The base color to generate the analogous scheme from.
     * @param numberOfColors The number of analogous colors to generate.
     * @return A list of `RGB` colors representing the analogous color scheme.
     */
    fun analogousScheme(baseColor: RGB, numberOfColors: Int): List<RGB> {
        val analogousColors = mutableListOf<RGB>()
        val hueShift = 30

        for (i in 0 until numberOfColors) {
            val shiftedColor = RGB(
                (baseColor.red + hueShift * i).coerceIn(0, 255),
                (baseColor.green + hueShift * i).coerceIn(0, 255),
                (baseColor.blue + hueShift * i).coerceIn(0, 255)
            )
            analogousColors.add(shiftedColor)
        }
        return analogousColors
    }

    /**
     * Generates a complementary color scheme based on a base color.
     * The complementary color is the color opposite to the base color on the color wheel.
     *
     * @param baseColor The base color to generate the complementary color scheme from.
     * @return A list of two `RGB` colors: the base color and its complementary color.
     */
    fun complementaryScheme(baseColor: RGB): List<RGB> {
        val complementaryColor = RGB(
            255 - baseColor.red,
            255 - baseColor.green,
            255 - baseColor.blue
        )
        return listOf(baseColor, complementaryColor)
    }

    /**
     * Generates a triadic color scheme based on a base color.
     * A triadic scheme consists of three colors that are evenly spaced on the color wheel,
     * with the base color being one of the points.
     *
     * @param baseColor The base color to generate the triadic scheme from.
     * @return A list of three `RGB` colors representing the triadic color scheme.
     */
    fun triadicScheme(baseColor: RGB): List<RGB> {
        val triadicColors = mutableListOf<RGB>()
        for (i in 0 until 3) {
            val shiftedColor = RGB(
                (baseColor.red + 85 * i).coerceIn(0, 255),
                (baseColor.green + 85 * i).coerceIn(0, 255),
                (baseColor.blue + 85 * i).coerceIn(0, 255)
            )
            triadicColors.add(shiftedColor)
        }
        return triadicColors
    }

    /**
     * Generates a tetradic (double complementary) color scheme based on a base color.
     * The tetradic scheme consists of two complementary color pairs, creating a diverse and balanced palette.
     *
     * @param baseColor The base color to generate the tetradic scheme from.
     * @return A list of four `RGB` colors representing the tetradic color scheme.
     */
    fun tetradicScheme(baseColor: RGB): List<RGB> {
        val firstComplementary = RGB(
            255 - baseColor.red,
            255 - baseColor.green,
            255 - baseColor.blue
        )
        val secondBaseColor = RGB(255 - baseColor.red, 255 - baseColor.green, baseColor.blue)
        val secondComplementary = RGB(
            255 - secondBaseColor.red,
            255 - secondBaseColor.green,
            255 - secondBaseColor.blue
        )

        return listOf(baseColor, firstComplementary, secondBaseColor, secondComplementary)
    }

    /**
     * Generates a square color scheme based on a base color.
     * The square color scheme consists of four colors that are evenly spaced around the color wheel.
     * This scheme offers a balanced combination of colors.
     *
     * @param baseColor The base color to generate the square color scheme from.
     * @return A list of four `RGB` colors representing the square color scheme.
     */
    fun generateSquareScheme(baseColor: RGB): List<RGB> {
        val squareColors = mutableListOf<RGB>()
        for (i in 0 until 4) {
            val shiftedColor = RGB(
                (baseColor.red + 64 * i).coerceIn(0, 255),
                (baseColor.green + 64 * i).coerceIn(0, 255),
                (baseColor.blue + 64 * i).coerceIn(0, 255)
            )
            squareColors.add(shiftedColor)
        }
        return squareColors
    }
}
