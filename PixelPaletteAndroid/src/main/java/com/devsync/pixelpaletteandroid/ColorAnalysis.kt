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

import android.graphics.Bitmap
import android.graphics.Color
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * The `ColorAnalysis` object provides utility functions to generate color histograms from images.
 * It includes both a sequential method and a parallelized method using Kotlin coroutines for improved performance.
 *
 * This object can be used to analyze the color distribution in an image by generating histograms of the red, green,
 * and blue color channels. The histograms are returned as instances of the [ColorHistogram] data class.
 *
 * Two methods are provided for generating the histogram:
 * - `histogram(bitmap: Bitmap)`: Generates the color histogram sequentially by iterating over all pixels in the image.
 * - `histogram(bitmap: Bitmap, parallelChunks: Int)`: Generates the color histogram in parallel by dividing the image into chunks and processing each chunk concurrently using Kotlin Coroutines.
 *
 * The color histograms are useful for various image processing tasks, such as color analysis, image enhancement, and feature extraction.
 */
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
     * @param bitmap The bitmap image from which the histogram is generated.
     * @return A [ColorHistogram] object containing the frequency of red, green, and blue values in the image.
     */
    fun histogram(bitmap: Bitmap): ColorHistogram {
        val redHistogram = IntArray(256)
        val greenHistogram = IntArray(256)
        val blueHistogram = IntArray(256)

        for (y in 0 until bitmap.height) {
            for (x in 0 until bitmap.width) {
                val pixel = bitmap.getPixel(x, y)

                val red = Color.red(pixel)
                val green = Color.green(pixel)
                val blue = Color.blue(pixel)

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
     * @param bitmap The bitmap image from which the histogram is generated.
     * @param parallelChunks The number of chunks to split the image into for parallel processing. The more chunks, the greater the level of parallelism.
     * @return A [ColorHistogram] object containing the frequency of red, green, and blue values in the image.
     * @throws [ArithmeticException] If the number of chunks is invalid (e.g., less than 1 or greater than the image height).
     */
    suspend fun histogram(bitmap: Bitmap, parallelChunks: Int): ColorHistogram = withContext(Dispatchers.Default) {
        val redHistogram = IntArray(256)
        val greenHistogram = IntArray(256)
        val blueHistogram = IntArray(256)

        val rowCount = bitmap.height
        val chunkSize = rowCount / parallelChunks

        coroutineScope {
            val jobs = mutableListOf<Job>()

            for (i in 0 until 4) {
                val startRow = i * chunkSize
                val endRow = if (i == 3) rowCount else (i + 1) * chunkSize

                jobs.add(launch {
                    for (y in startRow until endRow) {
                        for (x in 0 until bitmap.width) {
                            val pixel = bitmap.getPixel(x, y)
                            val red = Color.red(pixel)
                            val green = Color.green(pixel)
                            val blue = Color.blue(pixel)
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