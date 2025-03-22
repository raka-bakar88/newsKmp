package com.petros.efthymiou.dailypulse.articles

import com.petros.efthymiou.dailypulse.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticlesViewModel(private val useCase: ArticlesUseCase) : BaseViewModel() {
    private val _articlesState: MutableStateFlow<State<List<Article>>> = MutableStateFlow(State.Loading)
    val articleState: StateFlow<State<List<Article>>>
        get() = _articlesState

    init {
        getArticles()
    }

    fun getArticles(forceFetch: Boolean = false) {
        scope.launch {
            val fetchedArticles = useCase.getArticles(forceFetch)
            _articlesState.emit(State.Success(fetchedArticles))
        }
    }
}