package com.petros.efthymiou.dailypulse.articles.data

import com.petros.efthymiou.dailypulse.articles.data.ArticleRaw
import com.petros.efthymiou.dailypulse.articles.data.ArticlesResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArticlesService(private val httpClient:HttpClient) {
    private val country = "us"
    private val category = "business"
    private val apiKey = "884a05f67f654bad82ba169a9c638540"
    private val baseUrl = "https://newsapi.org/v2/"

    suspend fun fetchArticles(): List<ArticleRaw>{
        val response: ArticlesResponse = httpClient.get(baseUrl+"top-headlines?country=$country&category=$category&apiKey=$apiKey").body()
        return response.articles
    }
}