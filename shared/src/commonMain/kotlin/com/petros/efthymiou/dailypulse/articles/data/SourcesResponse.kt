package com.petros.efthymiou.dailypulse.articles.data

import kotlinx.serialization.SerialName

data class SourcesResponse(
    @SerialName("status")
    val status: String,
    @SerialName("articles")
    val articles: List<SourcesRaw>
)
