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

package com.devsync.pixelpalettejvm.utils

import java.awt.Color
import java.awt.image.BufferedImage
import java.util.concurrent.ForkJoinPool
import java.util.concurrent.atomic.AtomicInteger
import kotlin.math.sqrt

object ColorLab {

    fun extractColorsFromImage(image: BufferedImage): List<Color> {
        val colors = mutableListOf<Color>()
        val pool = ForkJoinPool.commonPool()
        val width = image.width
        val height = image.height
        val count = AtomicInteger(0)

        val tasks = mutableListOf<() -> Unit>()
        for (x in 0 until width) {
            tasks.add {
                for (y in 0 until height) {
                    val rgb = image.getRGB(x, y)
                    val color = Color(rgb, true)
                    if (color.alpha > 128) {
                        synchronized(colors) {
                            colors.add(color)
                        }
                    }
                    count.incrementAndGet()
                }
            }
        }

        tasks.forEach { pool.submit(it) }
        pool.shutdown()
        pool.awaitTermination(Long.MAX_VALUE, java.util.concurrent.TimeUnit.SECONDS)

        return colors
    }

    fun kMeans(colors: List<Color>, k: Int, maxIterations: Int = 100): List<Color> {
        var centroids = initializeCentroids(colors, k)
        var assignments = assignColorsToCentroids(colors, centroids)

        for (iteration in 0 until maxIterations) {
            val newCentroids = updateCentroids(colors, assignments, k)
            if (hasConverged(centroids, newCentroids)) {
                return newCentroids
            }
            centroids = newCentroids
            assignments = assignColorsToCentroids(colors, centroids)
        }

        return centroids
    }

    private fun initializeCentroids(colors: List<Color>, k: Int): List<Color> {
        return colors.shuffled().take(k)
    }

    private fun assignColorsToCentroids(colors: List<Color>, centroids: List<Color>): List<Int> {
        return colors.map { color ->
            centroids.indices.minByOrNull { index -> colorDistance(color, centroids[index]) } ?: 0
        }
    }

    private fun updateCentroids(colors: List<Color>, assignments: List<Int>, k: Int): List<Color> {
        val sumRed = IntArray(k)
        val sumGreen = IntArray(k)
        val sumBlue = IntArray(k)
        val counts = IntArray(k)

        for (i in colors.indices) {
            val color = colors[i]
            val cluster = assignments[i]
            sumRed[cluster] += color.red
            sumGreen[cluster] += color.green
            sumBlue[cluster] += color.blue
            counts[cluster]++
        }

        val newCentroids = mutableListOf<Color>()
        for (i in 0 until k) {
            val count = counts[i]
            if (count == 0) {
                newCentroids.add(Color(0, 0, 0))
            } else {
                newCentroids.add(Color(sumRed[i] / count, sumGreen[i] / count, sumBlue[i] / count))
            }
        }

        return newCentroids
    }

    private fun hasConverged(oldCentroids: List<Color>, newCentroids: List<Color>): Boolean {
        return oldCentroids.zip(newCentroids).all { (old, new) -> colorDistance(old, new) < 1.0 }
    }

    private fun colorDistance(c1: Color, c2: Color): Double {
        val dr = c1.red - c2.red
        val dg = c1.green - c2.green
        val db = c1.blue - c2.blue
        return sqrt((dr * dr + dg * dg + db * db).toDouble())
    }
}