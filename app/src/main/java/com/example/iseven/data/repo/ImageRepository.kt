package com.example.iseven.data.repo

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.Toast
import androidx.core.content.edit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedInputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.net.URL
import kotlin.io.path.Path
import kotlin.io.path.createDirectory
import kotlin.io.path.createFile
import kotlin.io.path.exists

class ImageRepository(private val applicationContext: Application) {
    private val sp = applicationContext.getSharedPreferences("lastImageIndex", Context.MODE_PRIVATE)
    private var imageCount: Int = sp.getInt("index", 0)

    suspend fun loadImage(string: String): Bitmap?{
        val stream = withContext(Dispatchers.IO) {
            URL(string).openStream()
        }
        try {
            val bufferedInputStream = BufferedInputStream(stream)
            return BitmapFactory.decodeStream(bufferedInputStream)
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG).show()
        }
        return null
    }

    suspend fun saveImage(bitmap: Bitmap){
        val path = Path("${applicationContext.filesDir}/images/${++imageCount}")
        if(!path.parent.exists()) path.parent.createDirectory()
        path.createFile()
        sp.edit { putInt("index", imageCount) }
        val stream = withContext(Dispatchers.IO) {
            FileOutputStream(path.toFile())
        }
        bitmap.compress(Bitmap.CompressFormat.PNG, 25, stream)
    }

    suspend fun getImages(): List<Bitmap> {
        val dir = File(applicationContext.filesDir.path + "/images/")
        println(dir.listFiles()?.joinToString(" "))
        return buildList<Bitmap> {
            dir.listFiles()?.forEach {
                val bis = BufferedInputStream(FileInputStream(it))
                val bitmap = BitmapFactory.decodeStream(bis)
                add(bitmap)
            }
        }
    }

    suspend fun removeImages(){
        val dir = File(applicationContext.filesDir.path + "/images/")
        dir.listFiles()?.forEach {
            it.delete()
        }
    }
}