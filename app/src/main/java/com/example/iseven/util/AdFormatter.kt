package com.example.iseven.util
import android.text.Html
import android.text.Spanned
import com.example.iseven.R
import kotlin.random.Random

object AdFormatter {
    private val colors = listOf("#FF0000", "#3333FF", "#33FF33", "#EE37FF")
    fun format(ad: String) : Spanned {
        return Html.fromHtml(buildString {
            for(word in ad.split(" ")){
                append("<font color=\"")
                append(colors.shuffled().first())
                append("\">")
                append(word)
                append(" </font>")
            }
        })
    }
}