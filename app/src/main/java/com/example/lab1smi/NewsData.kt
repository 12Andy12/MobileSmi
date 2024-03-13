package com.example.lab1smi

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class NewsData(
    val newsText: String = "",
    var likes: MutableState<Int> = mutableStateOf(0)
)