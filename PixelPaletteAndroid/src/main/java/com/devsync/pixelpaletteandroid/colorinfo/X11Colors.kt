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

package com.devsync.pixelpaletteandroid.colorinfo

import com.devsync.pixelpaletteandroid.model.CMYK
import com.devsync.pixelpaletteandroid.model.ColorInfo
import com.devsync.pixelpaletteandroid.model.RGB

object X11Colors {
    val x11Colors = listOf(
        ColorInfo("AliceBlue", RGB(240, 248, 255), "#F0F8FF", CMYK(6.27, 2.75, 0.0, 0.0)),
        ColorInfo("AntiqueWhite", RGB(250, 235, 215), "#FAEBD7", CMYK(0.0, 6.0, 13.6, 2.0)),
        ColorInfo("Aqua", RGB(0, 255, 255), "#00FFFF", CMYK(100.0, 0.0, 0.0, 0.0)),
        ColorInfo("Aquamarine", RGB(127, 255, 212), "#7FFFD4", CMYK(50.2, 0.0, 17.6, 0.0)),
        ColorInfo("Azure", RGB(240, 255, 255), "#F0FFFF", CMYK(6.27, 0.0, 0.0, 0.0)),
        ColorInfo("Beige", RGB(245, 245, 220), "#F5F5DC", CMYK(0.0, 0.0, 10.2, 4.3)),
        ColorInfo("Bisque", RGB(255, 228, 196), "#FFE4C4", CMYK(0.0, 10.2, 23.5, 0.0)),
        ColorInfo("Black", RGB(0, 0, 0), "#000000", CMYK(0.0, 0.0, 0.0, 100.0)),
        ColorInfo("BlanchedAlmond", RGB(255, 235, 205), "#FFEBCD", CMYK(0.0, 7.8, 19.6, 0.0)),
        ColorInfo("Blue", RGB(0, 0, 255), "#0000FF", CMYK(100.0, 100.0, 0.0, 0.0)),
        ColorInfo("BlueViolet", RGB(138, 43, 226), "#8A2BE2", CMYK(39.2, 81.9, 0.0, 11.8)),
        ColorInfo("Brown", RGB(165, 42, 42), "#A52A2A", CMYK(0.0, 74.6, 74.6, 35.3)),
        ColorInfo("BurlyWood", RGB(222, 184, 135), "#DEB887", CMYK(0.0, 17.0, 39.2, 12.9)),
        ColorInfo("CadetBlue", RGB(95, 158, 160), "#5F9EA0", CMYK(40.6, 1.3, 0.0, 37.6)),
        ColorInfo("Chartreuse", RGB(127, 255, 0), "#7FFF00", CMYK(50.2, 0.0, 100.0, 0.0)),
        ColorInfo("Chocolate", RGB(210, 105, 30), "#D2691E", CMYK(0.0, 50.0, 85.7, 17.6)),
        ColorInfo("Coral", RGB(255, 127, 80), "#FF7F50", CMYK(0.0, 50.2, 68.6, 0.0)),
        ColorInfo("CornflowerBlue", RGB(100, 149, 237), "#6495ED", CMYK(57.6, 37.1, 0.0, 7.8)),
        ColorInfo("Cornsilk", RGB(255, 248, 220), "#FFF8DC", CMYK(0.0, 2.7, 13.7, 0.0)),
        ColorInfo("Crimson", RGB(220, 20, 60), "#DC143C", CMYK(0.0, 90.9, 72.7, 13.7)),
        ColorInfo("Cyan", RGB(0, 255, 255), "#00FFFF", CMYK(0.0, 0.0, 0.0, 0.0)),
        ColorInfo("DarkBlue", RGB(0, 0, 139), "#00008B", CMYK(100.0, 100.0, 0.0, 45.9)),
        ColorInfo("DarkCyan", RGB(0, 139, 139), "#008B8B", CMYK(100.0, 0.0, 0.0, 45.9)),
        ColorInfo("DarkGoldenrod", RGB(184, 134, 11), "#B8860B", CMYK(0.0, 27.2, 94.0, 27.5)),
        ColorInfo("DarkGray", RGB(169, 169, 169), "#A9A9A9", CMYK(0.0, 0.0, 0.0, 33.3)),
        ColorInfo("DarkGreen", RGB(0, 100, 0), "#006400", CMYK(100.0, 0.0, 100.0, 60.8)),
        ColorInfo("DarkKhaki", RGB(189, 183, 107), "#BDB76B", CMYK(0.0, 3.2, 43.4, 25.5)),
        ColorInfo("DarkMagenta", RGB(139, 0, 139), "#8B008B", CMYK(0.0, 100.0, 0.0, 45.9)),
        ColorInfo("DarkOliveGreen", RGB(85, 107, 47), "#556B2F", CMYK(20.0, 0.0, 56.0, 58.8)),
        ColorInfo("DarkOrange", RGB(255, 140, 0), "#FF8C00", CMYK(0.0, 45.9, 100.0, 0.0)),
        ColorInfo("DarkOrchid", RGB(153, 50, 204), "#9932CC", CMYK(25.5, 75.5, 0.0, 20.0)),
        ColorInfo("DarkRed", RGB(139, 0, 0), "#8B0000", CMYK(0.0, 100.0, 100.0, 45.9)),
        ColorInfo("DarkSalmon", RGB(233, 150, 122), "#E9967A", CMYK(0.0, 35.4, 47.6, 8.6)),
        ColorInfo("DarkSeaGreen", RGB(143, 188, 143), "#8FBC8F", CMYK(23.4, 0.0, 23.4, 26.7)),
        ColorInfo("DarkSlateBlue", RGB(72, 61, 139), "#483D8B", CMYK(48.9, 56.0, 0.0, 45.9)),
        ColorInfo("DarkSlateGray", RGB(47, 79, 79), "#2F4F4F", CMYK(40.6, 0.0, 0.0, 69.8)),
        ColorInfo("DarkTurquoise", RGB(0, 206, 209), "#00CED1", CMYK(100.0, 1.5, 0.0, 18.8)),
        ColorInfo("DarkViolet", RGB(148, 0, 211), "#9400D3", CMYK(29.9, 100.0, 0.0, 17.6)),
        ColorInfo("DeepPink", RGB(255, 20, 147), "#FF1493", CMYK(0.0, 92.9, 42.8, 0.0)),
        ColorInfo("DeepSkyBlue", RGB(0, 191, 255), "#00BFFF", CMYK(100.0, 25.5, 0.0, 0.0)),
        ColorInfo("DimGray", RGB(105, 105, 105), "#696969", CMYK(0.0, 0.0, 0.0, 58.8)),
        ColorInfo("DodgerBlue", RGB(30, 144, 255), "#1E90FF", CMYK(88.2, 43.5, 0.0, 0.0)),
        ColorInfo("FireBrick", RGB(178, 34, 34), "#B22222", CMYK(0.0, 80.9, 80.9, 30.2)),
        ColorInfo("FloralWhite", RGB(255, 250, 240), "#FFFAF0", CMYK(0.0, 2.7, 5.9, 0.0)),
        ColorInfo("ForestGreen", RGB(34, 139, 34), "#228B22", CMYK(75.9, 0.0, 75.9, 45.9)),
        ColorInfo("Fuchsia", RGB(255, 0, 255), "#FF00FF", CMYK(0.0, 100.0, 0.0, 0.0)),
        ColorInfo("Gainsboro", RGB(220, 220, 220), "#DCDCDC", CMYK(0.0, 0.0, 0.0, 13.7)),
        ColorInfo("GhostWhite", RGB(248, 248, 255), "#F8F8FF", CMYK(2.7, 2.7, 0.0, 0.0)),
        ColorInfo("Gold", RGB(255, 215, 0), "#FFD700", CMYK(0.0, 15.7, 100.0, 0.0)),
        ColorInfo("Goldenrod", RGB(255, 215, 0), "#FFD700", CMYK(0.0, 15.7, 100.0, 0.0)),
        ColorInfo("Gray", RGB(128, 128, 128), "#808080", CMYK(0.0, 0.0, 0.0, 50.2)),
        ColorInfo("Green", RGB(0, 128, 0), "#008000", CMYK(100.0, 0.0, 100.0, 49.8)),
        ColorInfo("GreenYellow", RGB(173, 255, 47), "#ADFF2F", CMYK(32.2, 0.0, 81.6, 0.0)),
        ColorInfo("Honeydew", RGB(240, 255, 240), "#F0FFF0", CMYK(6.27, 0.0, 6.27, 0.0)),
        ColorInfo("HotPink", RGB(255, 105, 180), "#FF69B4", CMYK(0.0, 58.8, 29.4, 0.0)),
        ColorInfo("IndianRed", RGB(205, 92, 92), "#CD5C5C", CMYK(0.0, 55.6, 55.6, 19.6)),
        ColorInfo("Indigo", RGB(75, 0, 130), "#4B0082", CMYK(42.0, 100.0, 0.0, 49.0)),
        ColorInfo("Ivory", RGB(255, 255, 240), "#FFFFF0", CMYK(0.0, 0.0, 5.9, 0.0)),
        ColorInfo("Khaki", RGB(240, 230, 140), "#F0E68C", CMYK(0.0, 4.2, 41.2, 5.9)),
        ColorInfo("Lavender", RGB(230, 230, 250), "#E6E6FA", CMYK(8.0, 8.0, 0.0, 2.7)),
        ColorInfo("LavenderBlush", RGB(255, 240, 245), "#FFF0F5", CMYK(0.0, 6.7, 4.7, 0.0)),
        ColorInfo("LawnGreen", RGB(124, 252, 0), "#7CFC00", CMYK(50.2, 0.0, 100.0, 1.6)),
        ColorInfo("LemonChiffon", RGB(255, 250, 205), "#FFFACD", CMYK(0.0, 2.7, 19.6, 0.0)),
        ColorInfo("LightBlue", RGB(173, 216, 230), "#ADD8E6", CMYK(24.8, 6.5, 0.0, 9.8)),
        ColorInfo("LightCoral", RGB(240, 128, 128), "#F08080", CMYK(0.0, 46.7, 46.7, 5.9)),
        ColorInfo("LightCyan", RGB(224, 255, 255), "#E0FFFF", CMYK(12.2, 0.0, 0.0, 0.0)),
        ColorInfo("LightGoldenrodYellow", RGB(250, 250, 210), "#FAFAD2", CMYK(0.0, 0.0, 16.9, 2.0)),
        ColorInfo("LightGray", RGB(211, 211, 211), "#D3D3D3", CMYK(0.0, 0.0, 0.0, 17.6)),
        ColorInfo("LightGreen", RGB(144, 238, 144), "#90EE90", CMYK(39.2, 0.0, 39.2, 6.3)),
        ColorInfo("LightPink", RGB(255, 182, 193), "#FFB6C1", CMYK(0.0, 28.6, 24.7, 0.0)),
        ColorInfo("LightSalmon", RGB(255, 160, 122), "#FFA07A", CMYK(0.0, 37.6, 52.9, 0.0)),
        ColorInfo("LightSeaGreen", RGB(32, 178, 170), "#20B2AA", CMYK(82.2, 0.0, 4.0, 30.2)),
        ColorInfo("LightSkyBlue", RGB(135, 206, 250), "#87CEFA", CMYK(46.0, 17.6, 0.0, 2.0)),
        ColorInfo("LightSlateGray", RGB(119, 136, 153), "#778899", CMYK(22.2, 11.8, 0.0, 40.0)),
        ColorInfo("LightSteelBlue", RGB(176, 196, 222), "#B0C4DE", CMYK(20.0, 11.8, 0.0, 12.9)),
        ColorInfo("LightYellow", RGB(255, 255, 224), "#FFFFE0", CMYK(0.0, 0.0, 12.7, 0.0)),
        ColorInfo("Lime", RGB(0, 255, 0), "#00FF00", CMYK(100.0, 0.0, 100.0, 0.0)),
        ColorInfo("LimeGreen", RGB(50, 205, 50), "#32CD32", CMYK(75.9, 0.0, 75.9, 19.6)),
        ColorInfo("Linen", RGB(250, 240, 230), "#FAF0E6", CMYK(0.0, 4.0, 8.0, 2.0)),
        ColorInfo("Magenta", RGB(255, 0, 255), "#FF00FF", CMYK(0.0, 100.0, 0.0, 0.0)),
        ColorInfo("Maroon", RGB(128, 0, 0), "#800000", CMYK(0.0, 100.0, 100.0, 50.2)),
        ColorInfo("MediumAquamarine", RGB(102, 205, 170), "#66CDAA", CMYK(50.2, 0.0, 17.6, 19.6)),
        ColorInfo("MediumBlue", RGB(0, 0, 205), "#0000CD", CMYK(100.0, 100.0, 0.0, 19.6)),
        ColorInfo("MediumOrchid", RGB(186, 85, 211), "#BA55D3", CMYK(11.7, 59.8, 0.0, 17.6)),
        ColorInfo("MediumPurple", RGB(147, 112, 219), "#9370DB", CMYK(32.2, 48.9, 0.0, 13.7)),
        ColorInfo("MediumSeaGreen", RGB(60, 179, 113), "#3CB371", CMYK(66.7, 0.0, 37.6, 29.8)),
        ColorInfo("MediumSlateBlue", RGB(123, 104, 238), "#7B68EE", CMYK(48.9, 56.0, 0.0, 6.7)),
        ColorInfo("MediumSpringGreen", RGB(0, 250, 154), "#00FA9A", CMYK(100.0, 0.0, 38.8, 2.0)),
        ColorInfo("MediumTurquoise", RGB(72, 209, 204), "#48D1CC", CMYK(65.7, 0.0, 2.4, 18.8)),
        ColorInfo("MediumVioletRed", RGB(199, 21, 133), "#C71585", CMYK(0.0, 89.5, 33.3, 21.6)),
        ColorInfo("MidnightBlue", RGB(25, 25, 112), "#191970", CMYK(77.8, 77.8, 0.0, 56.9)),
        ColorInfo("MintCream", RGB(245, 255, 250), "#F5FFFA", CMYK(4.3, 0.0, 2.7, 0.0)),
        ColorInfo("MistyRose", RGB(255, 228, 225), "#FFE4E1", CMYK(0.0, 10.2, 11.4, 0.0)),
        ColorInfo("Moccasin", RGB(255, 228, 181), "#FFE4B5", CMYK(0.0, 10.2, 29.4, 0.0)),
        ColorInfo("NavajoWhite", RGB(255, 222, 173), "#FFDEAD", CMYK(0.0, 13.7, 32.5, 0.0)),
        ColorInfo("Navy", RGB(0, 0, 128), "#000080", CMYK(100.0, 100.0, 0.0, 50.2)),
        ColorInfo("OldLace", RGB(253, 245, 230), "#FDF5E6", CMYK(0.0, 3.2, 8.0, 0.8)),
        ColorInfo("Olive", RGB(128, 128, 0), "#808000", CMYK(0.0, 0.0, 100.0, 50.2)),
        ColorInfo("OliveDrab", RGB(107, 142, 35), "#6B8E23", CMYK(24.0, 0.0, 69.6, 44.7)),
        ColorInfo("Orange", RGB(255, 165, 0), "#FFA500", CMYK(0.0, 35.3, 100.0, 0.0)),
        ColorInfo("OrangeRed", RGB(255, 69, 0), "#FF4500", CMYK(0.0, 72.5, 100.0, 0.0)),
        ColorInfo("Orchid", RGB(218, 112, 214), "#DA70D6", CMYK(0.0, 48.4, 1.2, 14.5)),
        ColorInfo("PaleGoldenrod", RGB(238, 232, 170), "#EEE8AA", CMYK(0.0, 2.5, 28.6, 6.3)),
        ColorInfo("PaleGreen", RGB(152, 251, 152), "#98FB98", CMYK(39.2, 0.0, 39.2, 1.2)),
        ColorInfo("PaleTurquoise", RGB(175, 238, 238), "#AFEEEE", CMYK(26.7, 0.0, 0.0, 7.8)),
        ColorInfo("PaleVioletRed", RGB(219, 112, 147), "#D87093", CMYK(0.0, 49.3, 32.4, 13.7)),
        ColorInfo("PapayaWhip", RGB(255, 239, 213), "#FFEFD5", CMYK(0.0, 6.3, 16.9, 0.0)),
        ColorInfo("PeachPuff", RGB(255, 218, 185), "#FFDAB9", CMYK(0.0, 14.5, 27.5, 0.0)),
        ColorInfo("Peru", RGB(205, 133, 63), "#CD853F", CMYK(0.0, 35.6, 69.8, 19.6)),
        ColorInfo("Pink", RGB(255, 192, 203), "#FFC0CB", CMYK(0.0, 25.9, 20.0, 0.0)),
        ColorInfo("Plum", RGB(221, 160, 221), "#DDA0DD", CMYK(0.0, 27.1, 0.0, 13.7)),
        ColorInfo("PowderBlue", RGB(176, 224, 230), "#B0E0E6", CMYK(23.4, 2.7, 0.0, 10.2)),
        ColorInfo("Purple", RGB(128, 0, 128), "#800080", CMYK(0.0, 100.0, 0.0, 50.2)),
        ColorInfo("Red", RGB(255, 0, 0), "#FF0000", CMYK(0.0, 100.0, 100.0, 0.0)),
        ColorInfo("RosyBrown", RGB(188, 143, 143), "#BC8F8F", CMYK(0.0, 24.5, 24.5, 26.7)),
        ColorInfo("RoyalBlue", RGB(65, 105, 225), "#4169E1", CMYK(71.8, 53.3, 0.0, 11.8)),
        ColorInfo("SaddleBrown", RGB(139, 69, 19), "#8B4513", CMYK(0.0, 50.0, 86.0, 45.9)),
        ColorInfo("Salmon", RGB(250, 128, 114), "#FA8072", CMYK(0.0, 48.0, 54.1, 2.0)),
        ColorInfo("SandyBrown", RGB(244, 164, 96), "#F4A460", CMYK(0.0, 32.8, 60.8, 4.7)),
        ColorInfo("SeaGreen", RGB(46, 139, 87), "#2E8B57", CMYK(66.7, 0.0, 37.6, 45.9)),
        ColorInfo("SeaShell", RGB(255, 245, 238), "#FFF5EE", CMYK(0.0, 4.1, 6.7, 0.0)),
        ColorInfo("Sienna", RGB(160, 82, 45), "#A0522D", CMYK(0.0, 48.8, 71.3, 37.6)),
        ColorInfo("Silver", RGB(192, 192, 192), "#C0C0C0", CMYK(0.0, 0.0, 0.0, 25.5)),
        ColorInfo("SkyBlue", RGB(135, 206, 235), "#87CEEB", CMYK(46.0, 12.2, 0.0, 7.8)),
        ColorInfo("SlateBlue", RGB(106, 90, 205), "#6A5ACD", CMYK(48.9, 55.9, 0.0, 19.6)),
        ColorInfo("SlateGray", RGB(112, 128, 144), "#708090", CMYK(22.2, 11.8, 0.0, 43.9)),
        ColorInfo("Snow", RGB(255, 250, 250), "#FFFAFA", CMYK(0.0, 2.7, 2.7, 0.0)),
        ColorInfo("SpringGreen", RGB(0, 255, 127), "#00FF7F", CMYK(100.0, 0.0, 50.2, 0.0)),
        ColorInfo("SteelBlue", RGB(70, 130, 180), "#4682B4", CMYK(61.1, 27.4, 0.0, 29.8)),
        ColorInfo("Tan", RGB(210, 180, 140), "#D2B480", CMYK(0.0, 14.3, 33.3, 17.6)),
        ColorInfo("Teal", RGB(0, 128, 128), "#008080", CMYK(100.0, 0.0, 0.0, 50.2)),
        ColorInfo("Thistle", RGB(216, 191, 216), "#D8BFD8", CMYK(0.0, 11.8, 0.0, 15.7)),
        ColorInfo("Tomato", RGB(255, 99, 71), "#FF6347", CMYK(0.0, 61.2, 72.2, 0.0)),
        ColorInfo("Turquoise", RGB(64, 224, 208), "#40E0D0", CMYK(71.8, 0.0, 7.8, 12.9)),
        ColorInfo("Violet", RGB(238, 130, 238), "#EE82EE", CMYK(0.0, 45.3, 0.0, 6.7)),
        ColorInfo("Wheat", RGB(245, 222, 179), "#F5DEB3", CMYK(0.0, 9.4, 26.7, 4.7)),
        ColorInfo("White", RGB(255, 255, 255), "#FFFFFF", CMYK(0.0, 0.0, 0.0, 0.0)),
        ColorInfo("WhiteSmoke", RGB(245, 245, 245), "#F5F5F5", CMYK(0.0, 0.0, 0.0, 4.7)),
        ColorInfo("Yellow", RGB(255, 255, 0), "#FFFF00", CMYK(0.0, 0.0, 100.0, 0.0)),
        ColorInfo("YellowGreen", RGB(154, 205, 50), "#9ACD32", CMYK(24.0, 0.0, 75.9, 19.6))
    )
}