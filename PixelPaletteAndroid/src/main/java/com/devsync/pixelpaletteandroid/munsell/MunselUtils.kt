package com.devsync.pixelpaletteandroid.munsell

import com.devsync.pixelpaletteandroid.model.LAB
import com.devsync.pixelpaletteandroid.model.Munsell
import com.devsync.pixelpaletteandroid.model.MunsellColor
import kotlin.math.pow
import kotlin.math.sqrt

private fun euclideanDistance(lab1: LAB, lab2: LAB): Double {
    return sqrt(
        (lab1.lightness - lab2.lightness).pow(2) +
                (lab1.a - lab2.a).pow(2) +
                (lab1.b - lab2.b).pow(2)
    )
}

internal fun labToMunsell(lab: LAB, munsellDatabase: List<MunsellColor>): Munsell {
    val closestMunsell = munsellDatabase.minBy { munsellColor ->
        euclideanDistance(lab, munsellColor.lab)
    }
    return Munsell(closestMunsell.hue, closestMunsell.value, closestMunsell.chroma)
}