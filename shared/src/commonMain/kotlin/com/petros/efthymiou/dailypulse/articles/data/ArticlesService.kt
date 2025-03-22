package com.petros.efthymiou.dailypulse.articles.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArticlesService(private val httpClient:HttpClient) {
    private val country = "us"
    private val category = "business"
    private val apiKey = "COPY API KEY HERE"
    private val baseUrl = "https://newsapi.org/v2/"

    suspend fun fetchArticles(): List<ArticleRaw>{
        val response: ArticlesResponse = httpClient.get(baseUrl+"top-headlines?country=$country&category=$category&apiKey=$apiKey").body()
        return response.articles
    }

    suspend fun fetchSources():List<SourcesRaw>{
        val response : SourcesResponse = httpClient.get(baseUrl + "top-headlines/sources?apiKey=$apiKey").body()
        return response.articles
    }
}