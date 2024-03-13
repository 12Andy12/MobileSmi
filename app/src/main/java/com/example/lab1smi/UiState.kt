package com.example.lab1smi

data class UiState(
    val news: List<NewsData> = mutableListOf(),
    val index: Int = 0
)