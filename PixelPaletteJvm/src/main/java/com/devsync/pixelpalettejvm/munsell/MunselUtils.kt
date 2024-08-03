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

package com.devsync.pixelpalettejvm.munsell

import com.devsync.pixelpalettejvm.model.LAB
import com.devsync.pixelpalettejvm.model.Munsell
import com.devsync.pixelpalettejvm.model.MunsellColor
import kotlin.math.pow
import kotlin.math.sqrt

fun euclideanDistance(lab1: LAB, lab2: LAB): Double {
    return sqrt(
        (lab1.lightness - lab2.lightness).pow(2) +
                (lab1.a - lab2.a).pow(2) +
                (lab1.b - lab2.b).pow(2)
    )
}

fun labToMunsell(lab: LAB, munsellDatabase: List<MunsellColor>): Munsell {
    val closestMunsell = munsellDatabase.minBy { munsellColor ->
        euclideanDistance(lab, munsellColor.lab)
    }
    return Munsell(closestMunsell.hue, closestMunsell.value, closestMunsell.chroma)
}