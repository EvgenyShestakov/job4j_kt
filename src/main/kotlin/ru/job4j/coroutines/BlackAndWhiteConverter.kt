package ru.job4j.coroutines

import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

/**
 * Функция для загрузки изображения
 */
fun loadImage(name: String): BufferedImage {
    val stream = object {}.javaClass.classLoader.getResourceAsStream(name)
        ?: error("Resource not found: $name")
    return ImageIO.read(stream) ?: error("Can't read image from resource: $name")
}

/**
 * Функция для сохранения изображения
 */
fun saveImage(image: BufferedImage, path: String) {
    ImageIO.write(image, "jpg", File(path))
}

/**
 * Функция для конвертации пикселя в оттенок серого
 */
fun toGrayScale(color: Color): Color {
    val grayValue = (0.3 * color.red + 0.59 * color.green + 0.11 * color.blue).toInt()
    return Color(grayValue, grayValue, grayValue)
}

/**
 * Функция для обработки части изображения в корутине
 */
fun processImagePart(
    image: BufferedImage,
    startX: Int,
    endX: Int,
    startY: Int,
    endY: Int,
) {
    for (y in startY until endY) {
        for (x in startX until endX) {
            val rgb = image.getRGB(x, y)
            var color = Color(rgb)
            color = toGrayScale(color)
            image.setRGB(x, y, color.rgb)
        }
    }
}

fun main() {
    runBlocking {
        val inputImage = loadImage("input.jpg")
        val width = inputImage.width
        val height = inputImage.height
        val numParts = 4 // Количество частей для параллельной обработки
        val jobs = mutableListOf<Job>()
        val partWidth = width / numParts

        for (i in 0 until numParts) {
            val startX = i * partWidth
            val endX = if (i == numParts - 1) width else (i + 1) * partWidth

            val job = launch {
                processImagePart(inputImage, startX, endX, 0, height)
            }
            jobs.add(job)
        }

        jobs.forEach { it.join() }

        saveImage(inputImage, "output_grayscale.jpg")
    }
}