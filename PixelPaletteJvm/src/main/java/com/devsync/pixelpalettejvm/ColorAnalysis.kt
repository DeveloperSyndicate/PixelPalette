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

package com.devsync.pixelpalettejvm

import java.awt.Color
import java.awt.image.BufferedImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object ColorAnalysis {
    /**
     * Data class representing the color histogram of an image with individual histograms for red, green, and blue channels.
     *
     * @param red An array of size 256 representing the frequency of red values (0-255) in the image.
     * @param green An array of size 256 representing the frequency of green values (0-255) in the image.
     * @param blue An array of size 256 representing the frequency of blue values (0-255) in the image.
     */
    data class ColorHistogram(val red: IntArray, val green: IntArray, val blue: IntArray)

    /**
     * Generates a color histogram from an image without parallel processing.
     *
     * This method processes the entire image sequentially, scanning each pixel and incrementing the corresponding
     * histogram bins for red, green, and blue color channels.
     *
     * @param image The image (BufferedImage) from which the histogram is generated.
     * @return A [ColorHistogram] object containing the frequency of red, green, and blue values in the image.
     */
    fun histogram(image: BufferedImage): ColorHistogram {
        val redHistogram = IntArray(256)
        val greenHistogram = IntArray(256)
        val blueHistogram = IntArray(256)

        for (y in 0 until image.height) {
            for (x in 0 until image.width) {
                val pixel = image.getRGB(x, y)

                val red = Color(pixel).red
                val green = Color(pixel).green
                val blue = Color(pixel).blue

                redHistogram[red]++
                greenHistogram[green]++
                blueHistogram[blue]++
            }
        }

        return ColorHistogram(redHistogram, greenHistogram, blueHistogram)
    }

    /**
     * Generates a color histogram from an image using parallel processing for faster performance on large images.
     *
     * This method splits the image into multiple horizontal chunks and processes each chunk concurrently using Kotlin Coroutines.
     * The result is a color histogram with the frequency of red, green, and blue values, calculated in parallel.
     *
     * @param image The image (BufferedImage) from which the histogram is generated.
     * @param parallelChunks The number of chunks to split the image into for parallel processing. The more chunks, the greater the level of parallelism.
     * @return A [ColorHistogram] object containing the frequency of red, green, and blue values in the image.
     * @throws [ArithmeticException] If the number of chunks is invalid (e.g., less than 1 or greater than the image height).
     */
    suspend fun histogram(image: BufferedImage, parallelChunks: Int): ColorHistogram = withContext(Dispatchers.Default) {
        val redHistogram = IntArray(256)
        val greenHistogram = IntArray(256)
        val blueHistogram = IntArray(256)

        val rowCount = image.height
        val chunkSize = rowCount / parallelChunks

        if (parallelChunks < 1 || parallelChunks > rowCount) {
            throw ArithmeticException("Invalid number of chunks: $parallelChunks")
        }

        coroutineScope {
            val jobs = mutableListOf<Job>()

            for (i in 0 until parallelChunks) {
                val startRow = i * chunkSize
                val endRow = if (i == parallelChunks - 1) rowCount else (i + 1) * chunkSize

                jobs.add(launch {
                    for (y in startRow until endRow) {
                        for (x in 0 until image.width) {
                            val pixel = image.getRGB(x, y)
                            val red = Color(pixel).red
                            val green = Color(pixel).green
                            val blue = Color(pixel).blue

                            redHistogram[red]++
                            greenHistogram[green]++
                            blueHistogram[blue]++
                        }
                    }
                })
            }
            jobs.forEach { it.join() }
        }
        ColorHistogram(redHistogram, greenHistogram, blueHistogram)
    }
}