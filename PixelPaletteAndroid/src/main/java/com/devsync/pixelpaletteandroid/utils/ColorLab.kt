package com.devsync.pixelpaletteandroid.utils

import android.graphics.Bitmap
import android.graphics.Color
import com.devsync.pixelpaletteandroid.model.PixelColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Collections
import kotlin.math.sqrt

object ColorLab {

    suspend fun extractColorsFromImage(bitmap: Bitmap): List<PixelColor> = withContext(Dispatchers.Default) {
        val pixelColors: MutableList<PixelColor> = Collections.synchronizedList(mutableListOf<PixelColor>())
        val width = bitmap.width
        val height = bitmap.height

        coroutineScope {
            for (x in 0 until width step 10) {
                launch {
                    for (dx in 0 until 10) {
                        for (y in 0 until height) {
                            if (x + dx < width) {
                                val colorInt = bitmap.getPixel(x + dx, y)
                                if (Color.alpha(colorInt) > 128) {
                                    pixelColors.add(PixelColor(colorInt))
                                }
                            }
                        }
                    }
                }
            }
        }

        return@withContext pixelColors
    }


    suspend fun kMeans(colors: List<PixelColor>, k: Int, maxIterations: Int = 100): List<PixelColor> = withContext(Dispatchers.Default) {
        var centroids: List<PixelColor> = initializeCentroids(colors, k)
        var assignments = assignColorsToCentroids(colors, centroids)

        for (iteration in 0 until maxIterations) {
            val newCentroids = updateCentroids(colors, assignments, k)
            if (hasConverged(centroids, newCentroids)) {
                return@withContext newCentroids
            }
            centroids = newCentroids
            assignments = assignColorsToCentroids(colors, centroids)
        }

        return@withContext centroids
    }

    private fun initializeCentroids(colors: List<PixelColor>, k: Int): List<PixelColor> {
        return colors.shuffled().take(k)
    }

    private fun assignColorsToCentroids(colors: List<PixelColor>, centroids: List<PixelColor>): List<Int> {
        return colors.map { color ->
            centroids.indices.minByOrNull { centroids[it].distanceTo(color) }!!
        }
    }

    private fun updateCentroids(colors: List<PixelColor>, assignments: List<Int>, k: Int): List<PixelColor> {
        val newCentroids = MutableList(k) { PixelColor(0, 0, 0, 0) }
        val counts = MutableList(k) { 0 }

        for (i in colors.indices) {
            val centroidIndex = assignments[i]
            val color = colors[i]

            newCentroids[centroidIndex] = newCentroids[centroidIndex].copy(
                red = newCentroids[centroidIndex].red + color.red,
                green = newCentroids[centroidIndex].green + color.green,
                blue = newCentroids[centroidIndex].blue + color.blue,
                alpha = newCentroids[centroidIndex].alpha + color.alpha
            )
            counts[centroidIndex]++
        }

        return newCentroids.mapIndexed { index, centroid ->
            val count = counts[index]
            if (count == 0) centroid else PixelColor(
                centroid.red / count,
                centroid.green / count,
                centroid.blue / count,
                centroid.alpha / count
            )
        }
    }

    private fun hasConverged(oldCentroids: List<PixelColor>, newCentroids: List<PixelColor>): Boolean {
        return oldCentroids.zip(newCentroids).all { (old, new) -> old.distanceTo(new) < 1.0 }
    }

    private fun colorDistance(c1: Int, c2: Int): Double {
        val dr = Color.red(c1) - Color.red(c2)
        val dg = Color.green(c1) - Color.green(c2)
        val db = Color.blue(c1) - Color.blue(c2)
        return sqrt((dr * dr + dg * dg + db * db).toDouble())
    }
}
