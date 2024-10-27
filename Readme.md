# PixelPalette

**PixelPalette** is a powerful library for extracting dominant colors from images and generating color palettes. It supports both Android and JVM environments, allowing you to seamlessly integrate it into various projects. With `PixelPalette` library, you can easily find the most prominent colors in an image and create visually appealing palettes.

## Features

- **Dominant Color Extraction**: Identify the most prominent colors in an image.
- **Palette Generation**: Create a visual color palette from the extracted colors.
- **Color Conversion**: Convert between various color spaces and formats.
- **Cross-Platform Support**: Compatible with Android and JVM-based applications.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
    - [Android Integration](#android-integration)
    - [JVM Integration](#jvm-integration)
- [API Reference](#api-reference)
    - [Android API](#android-api)
    - [JVM API](#jvm-api)
    - [Color Conversion](#color-conversion)
- [Examples](#examples)
- [Contributing](#contributing)
- [License](#license)
- [Reporting Issues](#reporting-issues)

## Installation

### Gradle

Add the following dependency to your `build.gradle` file:

```gradle
dependencies {
    implementation 'io.github.developersyndicate:PixelPaletteAndroid:1.0.0'
}
```

```kotlin
dependencies {
    implementation("io.github.developersyndicate:PixelPaletteAndroid:1.0.0")
}
```
Replace 1.0.0 with the latest version of PixelPalette.

###  Maven
Add the following to your `pom.xml`:
```xml
<dependency>
  <groupId>io.github.developersyndicate</groupId>
  <artifactId>PixelPaletteAndroid</artifactId>
  <version>1.0.0</version>
</dependency>
```

### SBT
```
  libraryDependencies += "io.github.developersyndicate" % "PixelPaletteJvm" % "1.0.0"
```

## Usage
### Android Integration
1. **Add the Dependency:** Follow the Installation instructions for Gradle.

2. **Configure Permissions:** Ensure that your app has permission  to access the Image Access, internet if required (e.g., for downloading images).

3. Example Usage:
```kotlin
import android.graphics.Bitmap
import com.devsync.pixelpaletteandroid.PixelPalette
import com.devsync.pixelpaletteandroid.model.PixelColor

// Load an image bitmap (you can obtain this from your resources, camera, etc.)
val bitmap: Bitmap = ... 

// Extract dominant colors
val dominantColors: List<PixelColor> = PixelPalette.dominantColors(bitmap, clusterColor = 5)

// Generate a color palette image
val paletteModel = PixelPalette.createPalette(
    image = bitmap,
    paletteSize = 5,
    imageWidth = 800,
    imageHeight = 600,
    colorWidth = 100,
    colorHeight = 100
)
```

### JVM Integration
1. Add the Dependency: Follow the Installation instructions for Gradle or Maven.
2. Example Usage:
```kotlin
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import com.devsync.pixelpalettejvm.PixelPalette
import com.devsync.pixelpalettejvm.model.PaletteModel

// Load an image
val image: BufferedImage = ImageIO.read(File("path/to/image.jpg"))

// Extract dominant colors
val dominantColors: List<Color> = PixelPalette.dominantColors(image, clusterCount = 5)

// Generate a color palette image
val paletteModel: PaletteModel = PixelPalette.createPalette(
    image = image,
    paletteSize = 5,
    path = "path/to/output.png",
    imageWidth = 800,
    imageHeight = 600,
    colorWidth = 100,
    colorHeight = 100
)
```
# API Reference
## Android API

`PixelPalette.dominantColors(image: Bitmap, clusterColor: Int, downscaleFactor: Int = 2): List<PixelColor>`

Extracts the dominant colors from the provided Android Bitmap.

- **image:** The input image as a Bitmap.
- **clusterColor:** The number of dominant colors to return.
- **downscaleFactor:** Factor to downscale the image for faster processing.


  Returns a list of PixelColor objects representing the dominant colors.


`PixelPalette.createPalette(image: Bitmap, paletteSize: Int, path: String? = null, fileName: String? = null, imageWidth: Int = 600, imageHeight: Int = 800, colorWidth: Int = 600, colorHeight: Int = 100, downscaleFactor: Int = 1): PaletteModel`

Generates a color palette image from the provided Android Bitmap.

- **image:** The input image as a Bitmap.
- **paletteSize:** The number of colors in the palette.
- **path:** Optional path for saving the output image.
- **fileName:** Optional filename for the output image.
- **imageWidth:** Width of the output image.
- **imageHeight:** Height of the output image.
- **colorWidth:** Width of each color block in the palette image.
- **colorHeight:** Height of each color block in the palette image.
- **downscaleFactor:** Factor to downscale the image for faster processing.

Returns a PaletteModel containing the generated palette.

## JVM API

`PixelPalette.dominantColors(image: BufferedImage, clusterCount: Int): List<Color>
Extracts the dominant colors from the provided JVM BufferedImage.`

- **image:** The input image as a BufferedImage.
- **clusterCount:** The number of dominant colors to return.
- 
Returns a list of Color objects representing the dominant colors.


`PixelPalette.createPalette(image: BufferedImage, paletteSize: Int, path: String, imageWidth: Int = 600, imageHeight: Int = 800, colorWidth: Int = 600, colorHeight: Int = 100): PaletteModel` 

Generates a color palette image from the provided JVM `BufferedImage`.

- **image:** The input image as a BufferedImage.
- **paletteSize:** The number of colors in the palette.
- **path:** Path for saving the output image.
- **imageWidth:** Width of the output image.
- **imageHeight:** Height of the output image.
- **colorWidth:** Width of each color block in the palette image.
- **colorHeight:** Height of each color block in the palette image.

Returns a PaletteModel containing the generated palette.

## Color Conversion

**PixelPalette** provides several methods for converting between different color spaces and formats. Here are some of the available conversions:

### RGB to HEX

```kotlin
fun RGBtoHex(red: Int, green: Int, blue: Int): String
```
Converts RGB values to HEX format.

### RGB to CMYK

```kotlin
fun RGBtoCMYK(red: Int, green: Int, blue: Int): CMYK
```
Converts RGB values to CMYK color space.

### RGB to HSB

```kotlin
fun RGBtoHSB(red: Int, green: Int, blue: Int): HSB
```
Converts RGB values to HSB color space.
### RGB to HSL

```kotlin
fun RGBtoHSL(red: Int, green: Int, blue: Int): HSL
```
Converts RGB values to HSL color space.
### RGB to LAB

```kotlin
fun RGBtoLAB(r: Int, g: Int, b: Int): LAB
```
Converts RGB values to LAB color space.

### RGB to XYZ

```kotlin
fun RGBtoXYZ(red: Int, green: Int, blue: Int, scale: Int = 100): XYZ
```
Converts RGB values to XYZ color space.


### RGB to Pantone

```kotlin
fun RGBToPantone(red: Int, green: Int, blue: Int, pantones: List<Pantone>): Pantone?
```
Finds the closest Pantone color to the provided RGB values.
### RGB to RAL

```kotlin
fun RGBtoRAL(red: Int, green: Int, blue: Int, ral: List<RAL>): RAL
```
Finds the closest RAL color to the provided RGB values.

### RGB to Munsell
```kotlin
fun RGBtoMunsell(
  red: Int,
  green: Int,
  blue: Int,
  munsellDatabase: List<MunsellColor>,
): Munsell
```
Finds the closest Munsell color to the provided RGB values.

### RGB to HWB

```kotlin
fun RGBtoHWB(r: Int, g: Int, b: Int): HWB
```
Converts RGB values to HWB color space.

### RGB to sRGB

```kotlin
fun rgbToSRgb(r: Int, g: Int, b: Int): SRGB
```
Converts RGB values to sRGB color space.

### RGB to AdobeRGB

```kotlin
fun RGBToAdobeRGB(r: Int, g: Int, b: Int): XYZ
```
Converts RGB values to AdobeRGB color space.

### RGB to Rec2020

```kotlin
fun rgbToRec2020(r: Int, g: Int, b: Int): Rec2020
```
Converts RGB values to Rec2020 color space.

### RGB to CIELUV

```kotlin
fun RGBtoCIELUV(r: Int, g: Int, b: Int): CIELUV
```
Converts RGB values to CIELUV color space.

### RGB to CIELCH

```kotlin
fun RGBtoCIELCH(r: Int, g: Int, b: Int): CIELCH
```
Converts RGB values to CIELCH color space.

## Examples
### Android Example
```kotlin
import android.graphics.Bitmap
import com.devsync.pixelpaletteandroid.PixelPalette
import com.devsync.pixelpaletteandroid.model.PixelColor

fun generatePalette(bitmap: Bitmap): List<PixelColor> {
    return PixelPalette.dominantColors(bitmap, clusterColor = 5)
}
```

### JVM Example
```kotlin
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import com.devsync.pixelpalettejvm.PixelPalette
import com.devsync.pixelpalettejvm.model.PaletteModel

fun generatePalette(image: BufferedImage): PaletteModel {
    return PixelPalette.createPalette(
        image = image,
        paletteSize = 5,
        path = "path/to/output.png",
        imageWidth = 800,
        imageHeight = 600,
        colorWidth = 100,
        colorHeight = 100
    )
}
```

## Contributing
We welcome contributions to PixelPalette. To contribute, please follow these steps:

1. Fork the repository.
2. Create a feature branch (git checkout -b feature/YourFeature).
3. Commit your changes (git commit -am 'Add new feature').
4. Push to the branch (git push origin feature/YourFeature).
5. Create a new Pull Request.


## License
PixelPalette is licensed under the Apache 2.0 License. See the [License](LICENSE) file for more details.

## Reporting Issues
If you encounter any issues or bugs, please [create a new issue](https://github.com/DeveloperSyndicate/PixelPalette/issues) on GitHub. Provide a detailed description of the issue, steps to reproduce, and any relevant screenshots or code snippets.

