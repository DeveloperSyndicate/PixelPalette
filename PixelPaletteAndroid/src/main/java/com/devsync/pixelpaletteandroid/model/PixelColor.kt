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
