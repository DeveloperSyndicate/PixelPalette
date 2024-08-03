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

package com.devsync.pixelpaletteandroid.munsell

import com.devsync.pixelpaletteandroid.model.LAB
import com.devsync.pixelpaletteandroid.model.MunsellColor
import kotlin.math.cbrt
import kotlin.math.round

object MunsellColorsGenerator {

    //Generate shades for Single Hue
    fun generateShadesForHue(hue: String, numShades: Int): List<MunsellColor> {
        val valueRange = 10
        val chromaRange = 12

        val shades = mutableListOf<MunsellColor>()

        val stepValue = valueRange.toDouble() / cbrt(numShades.toDouble())
        val stepChroma = chromaRange.toDouble() / cbrt(numShades.toDouble())

        for (value in generateSequence(0.0) { it + stepValue }.takeWhile { it <= valueRange }) {
            for (chroma in generateSequence(0.0) { it + stepChroma }.takeWhile { it <= chromaRange }) {
                shades.add(
                    MunsellColor(
                        hue,
                        round(value * 10) / 10.0,
                        round(chroma * 10) / 10.0,
                        LAB(0.0, 0.0, 0.0)
                    )
                )
                if (shades.size >= numShades) break
            }
            if (shades.size >= numShades) break
        }

        return shades
    }

    //Generate Shades for All Hue
    fun generateShadesForAllHues(numShades: Int): List<MunsellColor> {
        val hues = listOf(
            "5R", "7R", "10R", "5YR", "7YR", "10YR", "5Y", "7Y", "10Y",
            "5G", "7G", "10G", "5BG", "7BG", "10BG", "5B", "7B", "10B",
            "5PB", "7PB", "10PB", "5P", "7P", "10P", "5RP", "7RP", "10RP"
        )
        val allShades = mutableListOf<MunsellColor>()

        for (hue in hues) {
            val hueShades = generateShadesForHue(hue, numShades / hues.size)
            allShades.addAll(hueShades)
        }

        return allShades
    }

}