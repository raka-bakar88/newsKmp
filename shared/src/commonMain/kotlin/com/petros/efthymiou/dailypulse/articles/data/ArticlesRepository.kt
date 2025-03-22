package com.petros.efthymiou.dailypulse.articles.data

import android.util.Log

class ArticlesRepository(
    private val dataSource: ArticlesDataSource,
    private val service: ArticlesService
) {
    suspend fun getArticles(forceFetch: Boolean): List<ArticleRaw>{
        if (forceFetch){
            dataSource.clearArticles()
            return fetchArticles()
        }
        val articleDb = dataSource.getAllArticles()
        Log.e("DB","Got ${articleDb.size} from the database")
        if (articleDb.isEmpty()){
            val fetchArticles = service.fetchArticles()
            dataSource.insertArticles(fetchArticles)
            return fetchArticles
        }

        return articleDb
    }

    private suspend fun fetchArticles(): List<ArticleRaw> {
        val fetchedArticles = service.fetchArticles()
        dataSource.insertArticles(fetchedArticles)
        return fetchedArticles
    }

    suspend fun getSources(): List<SourcesRaw>{
        val sources = service.fetchSources()
        return sources
    }
}