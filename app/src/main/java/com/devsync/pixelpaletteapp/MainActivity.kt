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
package com.devsync.pixelpaletteapp

import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.devsync.pixelpaletteandroid.PixelPalette
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Sample Usage For Dominant Color
        val imageBitmap: Bitmap? = null //Initialize your bitmap image
        lifecycleScope.launch {
            /**
             * Cluster Color - This Defines Number of Dominant color Needed
             *
             * Downscale Factor - To Reduce the quality of bitmap image, higher number means Faster
             * output but lesser accuracy
             *
             * */
            PixelPalette.dominantColors(image = imageBitmap!!, clusterColor = 5, downscaleFactor = 2)
        }

        //Sample Usage For Creating Color Palette
        val image: Bitmap? = null
        lifecycleScope.launch {
            PixelPalette.createPalette(image = image!!, paletteSize = 5, downscaleFactor = 5)
            /**
             * Create Palette Methods has the list of Params:
             * Image - Input Image as Bitmap
             * Palette Size - This Defines Number of Palette color Needed
             * Downscale Factor - To Reduce the quality of bitmap image, higher number means Faster
             *                    output but lesser accuracy
             * Path - Path for image output or nullable will generate image to Environment folders
             * FileName - Filename for the output image or nullable will generate with random name
             * Image Width/Height - Controls the total dimension of the image
             * Color Width/Height - Controls the dimension of each color in generated palette image
             * */
        }
    }
}