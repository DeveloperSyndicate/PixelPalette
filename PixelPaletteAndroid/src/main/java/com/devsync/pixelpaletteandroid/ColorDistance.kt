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

import com.devsync.pixelpaletteandroid.distance.*
import kotlin.math.*

object ColorDistance {

    /**
     * Calculates the Euclidean distance between two colors in the RGB color space.
     *
     * This function takes two colors, each represented by their RGB components (Red, Green, Blue),
     * and calculates the Euclidean distance between them. The Euclidean distance is calculated using the
     * following formula:
     *
     *  D = √((R1 - R2)² + (G1 - G2)² + (B1 - B2)²)
     *
     * Where (R1, G1, B1) are the RGB components of the first color, and (R2, G2, B2) are the RGB components
     * of the second color. The result is a numerical value representing the difference between the two colors in RGB space.
     *
     * This calculation is useful for comparing color similarity or distance between two colors. A smaller distance means
     * the colors are more similar, while a larger distance indicates a greater difference.
     *
     * @param color1 The first color to compare, represented as a Triple of RGB values (Red, Green, Blue).
     * @param color2 The second color to compare, represented as a Triple of RGB values (Red, Green, Blue).
     *
     * @return A `Double` representing the Euclidean distance between the two colors in RGB space.
     *         A smaller value indicates the colors are more similar, while a larger value indicates a greater difference.
     *
     * @throws IllegalArgumentException If either color contains invalid RGB values (i.e., outside the range 0-255).
     */
    fun euclideanDistance(color1: Triple<Int, Int, Int>, color2: Triple<Int, Int, Int>): Double {
        val (r1, g1, b1) = color1
        val (r2, g2, b2) = color2
        return sqrt((r1 - r2).toDouble().pow(2) + (g1 - g2).toDouble().pow(2) + (b1 - b2).toDouble().pow(2))
    }


    /**
     * Calculates the CIE Delta E (ΔE) color difference between two colors in the CIE Lab color space.
     *
     * The CIE Delta E (ΔE) formula calculates the perceptual difference between two colors in the
     * CIE Lab color space, which is designed to be more aligned with human color perception.
     * The Delta E value quantifies how different two colors are, and is based on the Euclidean distance
     * between the two colors' coordinates in the Lab color space, calculated as:
     *
     *  ΔE = √((L1 - L2)² + (a1 - a2)² + (b1 - b2)²)
     *
     * Where:
     * - L1, a1, b1 are the CIE Lab components of the first color.
     * - L2, a2, b2 are the CIE Lab components of the second color.
     *
     * A smaller ΔE indicates that the two colors are perceptually similar, while a larger ΔE indicates
     * a more noticeable difference between the colors. The Delta E scale can be used to assess the accuracy
     * of color reproduction or to compare colors in design or imaging applications.
     *
     * A ΔE of 1 is typically the smallest perceptible difference to the human eye, while a ΔE of 5 or more
     * is usually noticeable to most people.
     *
     * @param color1 The first color to compare, represented as a Triple of Lab values (L, a, b).
     * @param color2 The second color to compare, represented as a Triple of Lab values (L, a, b).
     *
     * @return A `Double` representing the Delta E (ΔE) color difference between the two colors.
     *         The smaller the value, the more similar the colors are perceptually.
     *
     * @throws IllegalArgumentException If the Lab components of the colors are outside valid ranges (L: 0-100, a: -128 to 127, b: -128 to 127).
     */
    fun deltaE(color1: Triple<Double, Double, Double>, color2: Triple<Double, Double, Double>): Double {
        val (l1, a1, b1) = color1
        val (l2, a2, b2) = color2

        return sqrt((l1 - l2).pow(2) + (a1 - a2).pow(2) + (b1 - b2).pow(2))
    }


    /**
     * Calculates the weighted Euclidean distance between two colors in the RGB color space.
     *
     * This function computes the distance between two colors in RGB space, but with different weights applied
     * to the Red, Green, and Blue components. This allows the comparison to prioritize certain color channels
     * (such as Red or Green) based on the specific needs of the application. The formula for the weighted
     * Euclidean distance is:
     *
     *  D_weighted = √(wR * (R1 - R2)² + wG * (G1 - G2)² + wB * (B1 - B2)²)
     *
     * Where:
     * - R1, G1, B1 are the RGB components of the first color.
     * - R2, G2, B2 are the RGB components of the second color.
     * - wR, wG, wB are the weights for the Red, Green, and Blue components, respectively.
     *
     * The weight values control how much influence each color channel has on the final distance. For instance,
     * a larger weight for Red (wR) means that differences in the Red channel will have a greater impact on the distance.
     *
     * @param color1 The first color to compare, represented as a Triple of RGB values (Red, Green, Blue).
     * @param color2 The second color to compare, represented as a Triple of RGB values (Red, Green, Blue).
     * @param wR The weight for the Red channel. A higher value gives more importance to Red.
     * @param wG The weight for the Green channel. A higher value gives more importance to Green.
     * @param wB The weight for the Blue channel. A higher value gives more importance to Blue.
     *
     * @return A `Double` representing the weighted Euclidean distance between the two colors in RGB space.
     *         The smaller the value, the more similar the colors are, with the weights influencing the result.
     *
     * @throws IllegalArgumentException If any RGB component of the colors is outside the valid range of 0 to 255,
     *                                  or if any weight is negative.
     */
    fun weightedRGBDistance(color1: Triple<Int, Int, Int>, color2: Triple<Int, Int, Int>, wR: Double, wG: Double, wB: Double): Double {
        val (r1, g1, b1) = color1
        val (r2, g2, b2) = color2

        if (r1 !in 0..255 || g1 !in 0..255 || b1 !in 0..255 || r2 !in 0..255 || g2 !in 0..255 || b2 !in 0..255) {
            throw IllegalArgumentException("RGB values must be in the range 0 to 255.")
        }
        if (wR < 0 || wG < 0 || wB < 0) {
            throw IllegalArgumentException("Weights must be non-negative.")
        }

        return sqrt(
            wR * (r1 - r2).toDouble().pow(2) +
                    wG * (g1 - g2).toDouble().pow(2) +
                    wB * (b1 - b2).toDouble().pow(2)
        )
    }


    /**
     * Calculates the CIE 94 (ΔE94) color difference between two colors in the CIE Lab color space.
     *
     * The CIE 94 (ΔE94) formula calculates the perceptual difference between two colors in the CIE Lab color space.
     * It refines the Euclidean distance method by considering the Lightness (L), Chroma (C), and Hue (H) components
     * separately, applying different weights based on their perceptual influence. The formula is:
     *
     *  ΔE94 = √((ΔL* / kL)² + (ΔC* / kC)² + (ΔH* / kH)² + RT * (ΔC* / kC) * (ΔH* / kH))
    *
    * Where:
    * - ΔL* is the difference in lightness.
    * - ΔC* is the difference in chroma.
    * - ΔH* is the difference in hue.
    * - kL, kC, kH are the weighting factors for lightness, chroma, and hue, respectively.
    * - RT is a factor that accounts for the interaction between chroma and hue.
    *
    * This method provides a more accurate color difference perception compared to the simple Euclidean distance, especially
    * in industries where small color differences are crucial, like printing and textiles.
    *
    * @param color1 The first color to compare, represented as a Triple of Lab values (L, a, b).
    * @param color2 The second color to compare, represented as a Triple of Lab values (L, a, b).
    * @param kL The weighting factor for Lightness (L*). Typically, kL = 1.
    * @param kC The weighting factor for Chroma (C*). Typically, kC = 1.
    * @param kH The weighting factor for Hue (H*). Typically, kH = 1.
    * @param RT The factor for chroma-hue interaction. Typically, RT = 0.
    *
    * @return A `Double` representing the ΔE94 color difference between the two colors. A smaller value indicates more
    *         similarity, and a larger value indicates a perceptible difference.
    *
    * @throws IllegalArgumentException If any Lab component is outside valid ranges or if any factor is negative.
    */
    fun cie94(color1: Triple<Double, Double, Double>, color2: Triple<Double, Double, Double>, kL: Double, kC: Double, kH: Double, RT: Double): Double {
        val (l1, a1, b1) = color1
        val (l2, a2, b2) = color2

        val deltaL = l1 - l2
        val deltaA = a1 - a2
        val deltaB = b1 - b2
        val deltaC = sqrt(a1.pow(2) + b1.pow(2)) - sqrt(a2.pow(2) + b2.pow(2))
        val deltaH = sqrt((deltaA).pow(2) + (deltaB).pow(2) - deltaC.pow(2))

        return sqrt(
            (deltaL / kL).pow(2) +
                    (deltaC / kC).pow(2) +
                    (deltaH / kH).pow(2) +
                    RT * (deltaC / kC) * (deltaH / kH)
        )
    }

    /**
     * Calculates the CIE 2000 (ΔE2000) color difference between two colors in the CIE Lab color space.
     *
     * The CIE 2000 (ΔE2000) formula refines the color difference calculation by incorporating a more perceptually uniform
     * model, including corrections for lightness, chroma, and hue differences. It also considers the viewing angle and
     * light source, making it more accurate than previous color difference formulas like CIE 94.
     * The formula is:
     *
     *  ΔE2000 = √((ΔL* /SL)² + (ΔC* /SC)² + (ΔH* /SH)² + RT * (ΔC* /SC) * (ΔH* /SH))
    *
    * Where:
    * - ΔL* is the difference in lightness.
    * - ΔC* is the difference in chroma.
    * - ΔH* is the difference in hue.
    * - SL, SC, SH are the scaling factors for lightness, chroma, and hue.
    * - RT is the chroma-hue interaction term, calculated based on hue differences.
    *
    * This method is widely used for more accurate color difference calculations that are perceptually uniform across
    * various lighting conditions, making it suitable for critical color applications.
    *
    * @param color1 The first color to compare, represented as a Triple of Lab values (L, a, b).
    * @param color2 The second color to compare, represented as a Triple of Lab values (L, a, b).
    * @param SL The scaling factor for Lightness (L*). Typically, SL = 1.
    * @param SC The scaling factor for Chroma (C*). Typically, SC = 1.
    * @param SH The scaling factor for Hue (H*). Typically, SH = 1.
    * @param RT The chroma-hue interaction term. Calculated based on hue difference.
    *
    * @return A `Double` representing the ΔE2000 color difference between the two colors. A smaller value indicates more
    *         similarity, and a larger value indicates a perceptible difference.
    *
    * @throws IllegalArgumentException If any Lab component is outside valid ranges or if any scaling factor is negative.
    */
    fun cie2000(color1: Triple<Double, Double, Double>, color2: Triple<Double, Double, Double>, SL: Double, SC: Double, SH: Double, RT: Double): Double {
        val (l1, a1, b1) = color1
        val (l2, a2, b2) = color2

        val deltaL = l1 - l2
        val deltaA = a1 - a2
        val deltaB = b1 - b2
        val deltaC = sqrt(a1.pow(2) + b1.pow(2)) - sqrt(a2.pow(2) + b2.pow(2))
        val deltaH = sqrt((deltaA).pow(2) + (deltaB).pow(2) - deltaC.pow(2))

        return sqrt(
            (deltaL / SL).pow(2) +
                    (deltaC / SC).pow(2) +
                    (deltaH / SH).pow(2) +
                    RT * (deltaC / SC) * (deltaH / SH)
        )
    }

    /**
     * Calculates the Euclidean distance between two colors in the HSB/HSV color space.
     *
     * This function computes the distance between two colors in the HSB/HSV color space using the Euclidean distance
     * formula. The formula is:
     *
     *  D_HSV = √((ΔH)² + (ΔS)² + (ΔV)²)
     *
     * Where:
     * - ΔH is the difference in Hue (H), which is cyclic in the range [0, 360).
     * - ΔS is the difference in Saturation (S), which is in the range [0, 1].
     * - ΔV is the difference in Value (V) or Brightness (B), which is in the range [0, 1].
     *
     * The function calculates the shortest angular distance for the Hue component, which wraps around at 360 degrees.
     * This makes the Hue difference more perceptually accurate.
     *
     * @param color1 The first color to compare, represented as a Triple of HSB/HSV values (Hue, Saturation, Brightness/Value).
     * @param color2 The second color to compare, represented as a Triple of HSB/HSV values (Hue, Saturation, Brightness/Value).
     *
     * @return A `Double` representing the color distance in HSB/HSV space. A smaller value indicates that the colors are more similar.
     *
     * @throws IllegalArgumentException If any HSB/HSV component is outside the valid range.
     */
    fun hsbHsvDistance(color1: Triple<Double, Double, Double>, color2: Triple<Double, Double, Double>): Double {
        val (h1, s1, v1) = color1
        val (h2, s2, v2) = color2

        val deltaH = minOf(abs(h1 - h2), 360 - abs(h1 - h2))
        val deltaS = s1 - s2
        val deltaV = v1 - v2

        return sqrt(deltaH.pow(2) + deltaS.pow(2) + deltaV.pow(2))
    }

    /**
     * Calculates the Manhattan distance (L1 distance) between two colors in the RGB color space.
     *
     * The Manhattan distance measures the difference between two colors by summing the absolute differences of
     * their red, green, and blue components. Unlike the Euclidean distance, which uses squared differences,
     * the Manhattan distance is linear and does not consider the geometric distance but simply the
     * cumulative difference along each axis.
     *
     * The formula is as follows:
     * <pre>
     * D = |ΔR| + |ΔG| + |ΔB|
     * </pre>
     * Where:
     * - ΔR is the difference between the red component of the two colors.
     * - ΔG is the difference between the green component of the two colors.
     * - ΔB is the difference between the blue component of the two colors.
     *
     * This method is computationally simpler than the Euclidean distance and can be useful in scenarios where
     * a more linear color difference is desired, or when you need a faster approximation of color differences.
     *
     * @param color1 The first color to compare, represented as an object or array with RGB components.
     * @param color2 The second color to compare, represented as an object or array with RGB components.
     *
     * @return A `Double` representing the Manhattan distance between the two colors. A smaller value indicates
     *         more similarity between the colors, and a larger value indicates greater difference.
     *
     * @throws IllegalArgumentException If the RGB values of either color are out of valid ranges (0-255).
     */
    fun manhattanDistance(color1: Triple<Int, Int, Int>, color2: Triple<Int, Int, Int>): Int {
        val (r1, g1, b1) = color1
        val (r2, g2, b2) = color2

        return abs(r1 - r2) + abs(g1 - g2) + abs(b1 - b2)
    }

    /**
     * Calculates the Mahalanobis distance between two colors in the RGB color space, considering the covariance
     * between the components.
     *
     * The Mahalanobis distance is a multivariate measure of distance that accounts for the correlation between
     * different dimensions (in this case, the RGB components). Unlike the Euclidean distance, which assumes
     * that the dimensions are independent, the Mahalanobis distance uses the covariance matrix of the color
     * components to adjust the scale of the differences.
     *
     * The formula is as follows:
     * <pre>
     * D = √((x - μ)ᵀ Σ⁻¹ (x - μ))
     * </pre>
     * Where:
     * - `x` is the vector representing the color in the RGB space (a set of RGB components).
     * - `μ` is the mean of the RGB components.
     * - `Σ⁻¹` is the inverse of the covariance matrix of the RGB components.
     *
     * This distance measure is particularly useful in cases where the color components are not independent,
     * for instance when working with real-world images or when there's an inherent correlation between the color channels.
     *
     * @param color1 The first color to compare, represented as an object or array with RGB components.
     * @param color2 The second color to compare, represented as an object or array with RGB components.
     * @param covarianceMatrix The covariance matrix of the RGB components, representing the correlations between them.
     *
     * @return A `Double` representing the Mahalanobis distance between the two colors. A smaller value indicates
     *         that the colors are more similar when considering their covariance structure.
     *
     * @throws IllegalArgumentException If the covariance matrix is not invertible or if the color values are out of valid ranges (0-255).
     */
    fun mahalanobisDistance(color1: Triple<Int, Int, Int>, color2: Triple<Int, Int, Int>, covarianceMatrix: Array<DoubleArray>): Double {
        val (r1, g1, b1) = color1
        val (r2, g2, b2) = color2
        val x = doubleArrayOf(r1 - r2.toDouble(), g1 - g2.toDouble(), b1 - b2.toDouble())
        val invCovarianceMatrix = invertMatrix(covarianceMatrix)
        val distance = calculateMahalanobisDistance(x, invCovarianceMatrix)

        return distance
    }


    /**
     * Calculates the chroma difference between two colors in the HSB/HSV color space.
     *
     * The chroma difference is the difference in the chromatic intensity (saturation and brightness/value)
     * between two colors. It is calculated as the Euclidean distance between the chroma components
     * (Saturation and Brightness/Value) of the two colors.
     *
     * @param color1 The first color to compare, represented in HSB/HSV as a Triple(Saturation, Brightness).
     * @param color2 The second color to compare, represented in HSB/HSV as a Triple(Saturation, Brightness).
     *
     * @return A `Double` representing the chroma difference between the two colors.
     */
    fun chromaDifferenceHSB(color1: Triple<Float, Float, Float>, color2: Triple<Float, Float, Float>): Float {
        val (s1, b1, _) = color1
        val (s2, b2, _) = color2

        val chroma1 = sqrt(s1 * s1 + b1 * b1)
        val chroma2 = sqrt(s2 * s2 + b2 * b2)

        return abs(chroma1 - chroma2)
    }


    /**
     * Calculates the chroma difference between two colors in the CIE 1976 (Lab) color space.
     *
     * The chroma difference is the difference in chromatic intensity (a* and b* components)
     * between two colors. It is calculated as the Euclidean distance between the chroma components
     * (a* and b*) of the two colors.
     *
     * @param color1 The first color to compare, represented in CIE 1976 (Lab) as a Triple(a*, b*).
     * @param color2 The second color to compare, represented in CIE 1976 (Lab) as a Triple(a*, b*).
     *
     * @return A `Double` representing the chroma difference between the two colors.
     */
    fun chromaDifferenceLab(color1: Triple<Float, Float, Float>, color2: Triple<Float, Float, Float>): Float {
        val (a1, b1, _) = color1
        val (a2, b2, _) = color2

        val chroma1 = sqrt(a1 * a1 + b1 * b1)
        val chroma2 = sqrt(a2 * a2 + b2 * b2)

        return abs(chroma1 - chroma2)
    }


    /**
     * Calculates the chroma difference between two colors in the RGB color space.
     *
     * Chroma in the RGB color space is the difference between the maximum and minimum
     * values of the red, green, and blue components. The chroma difference is then the
     * absolute difference between the chroma values of two colors.
     *
     * @param color1 The first color to compare, represented as an array of three floats [R, G, B].
     * @param color2 The second color to compare, represented as an array of three floats [R, G, B].
     *
     * @return A `Double` representing the chroma difference between the two colors.
     */
    fun chromaDifferenceRGB(color1: FloatArray, color2: FloatArray): Double {
        if (color1.size != 3 || color2.size != 3) {
            throw IllegalArgumentException("Both colors must be represented as an array of three values (R, G, B).")
        }

        val chroma1 = calculateChroma(color1)
        val chroma2 = calculateChroma(color2)

        return abs(chroma1 - chroma2)
    }
}