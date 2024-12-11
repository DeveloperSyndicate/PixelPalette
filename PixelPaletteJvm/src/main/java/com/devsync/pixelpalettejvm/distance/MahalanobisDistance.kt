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

package com.devsync.pixelpalettejvm.distance

import kotlin.math.sqrt

/**
 * Inverts a given matrix (3x3) using the Gaussian elimination method.
 *
 * This function inverts a 3x3 matrix by using Gaussian elimination.
 * It assumes the matrix is square and invertible. If the matrix is not invertible,
 * an exception will be thrown.
 *
 * @param matrix The matrix to invert, represented as a 2D array of doubles.
 * @return The inverse of the matrix, represented as a 2D array of doubles.
 * @throws IllegalArgumentException If the matrix is not invertible (singular matrix).
 */
internal fun invertMatrix(matrix: Array<DoubleArray>): Array<DoubleArray> {
    if (matrix.size != 3 || matrix[0].size != 3) {
        throw IllegalArgumentException("Matrix must be 3x3.")
    }

    val det = determinant(matrix)
    if (det == 0.0) {
        throw IllegalArgumentException("Matrix is not invertible (determinant is zero).")
    }

    val minors = Array(3) { DoubleArray(3) }
    for (i in 0 until 3) {
        for (j in 0 until 3) {
            minors[i][j] = minor(matrix, i, j)
        }
    }

    val cofactorMatrix = Array(3) { DoubleArray(3) }
    for (i in 0 until 3) {
        for (j in 0 until 3) {
            cofactorMatrix[i][j] = minors[i][j] * (if ((i + j) % 2 == 0) 1 else -1)
        }
    }

    val adjugate = Array(3) { DoubleArray(3) }
    for (i in 0 until 3) {
        for (j in 0 until 3) {
            adjugate[i][j] = cofactorMatrix[j][i]
        }
    }

    val inverseMatrix = Array(3) { DoubleArray(3) }
    for (i in 0 until 3) {
        for (j in 0 until 3) {
            inverseMatrix[i][j] = adjugate[i][j] / det
        }
    }

    return inverseMatrix
}

/**
 * Calculates the determinant of a 3x3 matrix.
 *
 * @param matrix The matrix to calculate the determinant for.
 * @return The determinant of the matrix.
 */
internal fun determinant(matrix: Array<DoubleArray>): Double {
    val a = matrix[0][0]
    val b = matrix[0][1]
    val c = matrix[0][2]
    val d = matrix[1][0]
    val e = matrix[1][1]
    val f = matrix[1][2]
    val g = matrix[2][0]
    val h = matrix[2][1]
    val i = matrix[2][2]

    return a * (e * i - f * h) - b * (d * i - f * g) + c * (d * h - e * g)
}

/**
 * Calculates the minor of a matrix element (removes the row and column of the element).
 *
 * @param matrix The matrix to calculate the minor for.
 * @param row The row index of the element.
 * @param col The column index of the element.
 * @return The minor of the element.
 */
internal fun minor(matrix: Array<DoubleArray>, row: Int, col: Int): Double {
    val subMatrix = Array(2) { DoubleArray(2) }

    var subi = 0
    for (i in 0 until 3) {
        if (i == row) continue
        var subj = 0
        for (j in 0 until 3) {
            if (j == col) continue
            subMatrix[subi][subj] = matrix[i][j]
            subj++
        }
        subi++
    }

    return subMatrix[0][0] * subMatrix[1][1] - subMatrix[0][1] * subMatrix[1][0]
}

/**
 * Calculates the Mahalanobis distance using the formula (x - μ)ᵀ Σ⁻¹ (x - μ).
 *
 * @param x The difference vector between the colors (x - μ), represented as a DoubleArray.
 * @param invCovarianceMatrix The inverse of the covariance matrix Σ⁻¹, represented as a 2D array of doubles.
 * @return The Mahalanobis distance as a Double.
 */
internal fun calculateMahalanobisDistance(x: DoubleArray, invCovarianceMatrix: Array<DoubleArray>): Double {
    if (x.size != 3 || invCovarianceMatrix.size != 3 || invCovarianceMatrix[0].size != 3) {
        throw IllegalArgumentException("x must be a vector of length 3 and covariance matrix must be 3x3.")
    }

    var result = 0.0
    for (i in 0 until 3) {
        var sum = 0.0
        for (j in 0 until 3) {
            sum += x[j] * invCovarianceMatrix[j][i]
        }
        result += sum * x[i]
    }

    return sqrt(result)
}