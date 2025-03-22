package com.petros.efthymiou.dailypulse.articles.application

import com.petros.efthymiou.dailypulse.articles.data.ArticleRaw
import com.petros.efthymiou.dailypulse.articles.data.ArticlesRepository
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import kotlin.math.abs

class ArticlesUseCase(private val repository: ArticlesRepository) {
    suspend fun getArticles(forceFetch: Boolean): List<Article>{
        val articlesRaw = repository.getArticles(forceFetch)
        return mapArticles(articlesRaw)
    }

    private fun mapArticles(articlesRaw: List<ArticleRaw>): List<Article> = articlesRaw.map { raw->
        Article(
            title = raw.title,
            desc = raw.desc ?: "Click to find out more",
            date = getDaysAgoString(raw.date),
            imageUrl = raw.imageUrl ?: "https://upload.wikimedia.org/wikipedia/commons/1/14/No_Image_Available.jpg"

        )
    }
    private fun getDaysAgoString(date:String):String{
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val days = today.daysUntil(
            Instant.parse(date).toLocalDateTime(TimeZone.currentSystemDefault()).date
        )

        val result = when{
            abs(days) > 1 -> "${abs(days)} days ago"
            abs(days) == 1 -> "Yesterday"
            else -> "Today"
        }

        return result
    }
}