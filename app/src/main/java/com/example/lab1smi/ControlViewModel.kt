package com.example.lab1smi

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Timer
import kotlin.concurrent.schedule

class ControlViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val allNews = listOf(
        NewsData(newsText = "Актер сыгравший Виктора Баринова усыновил Огузка"),
        NewsData(newsText = "Жена обиделась на насмешку мужа и развелась с ним через три минуты после свадебной церемонии"),
        NewsData(newsText = "Сталин победил на выборах в индии"),
        NewsData(newsText = "Девушку раздрожает бойфренд, который надевает ее футболки и играет в пейнтбол"),
        NewsData(newsText = "Эксперты считают, что для борьбы с похмельем нужно есть кимчи и пить чайный гриб"),
        NewsData(newsText = "Голодный кабан напал на велосипедистку, он отобрал у нее слойки"),
        NewsData(newsText = "Крысы расплодились в аптеке и питаются детскими молочными смесями"),
        NewsData(newsText = "Человек в костюме пиццы с куском крысы бегает в метро. Всем плевать"),
        NewsData(newsText = "Студент съел подогретые макароны и скончался от рака подгубной железы"),
        NewsData(newsText = "С дома культуры украли костюм пиццы и кусок крысы, злоумышленник не найден")
    )

    private val usedNews = mutableListOf<NewsData>()

    private fun generateRandomNews(): NewsData{
        val index =  (0..9).random()
        val news = allNews[index]
        if (usedNews.contains(news)){
            return generateRandomNews()
        }
        return news
    }

    private fun updateNews(){
        usedNews[_uiState.value.index] = generateRandomNews()
        updateIndex(_uiState.value.index.inc().mod(_uiState.value.news.size - 1))
    }

    private fun updateIndex(index: Int){
        _uiState.update { currentState ->
            currentState.copy(index = index)
        }
    }

    init {
        for(i in (0..4)){
            val news = generateRandomNews()
            usedNews.add(news)
        }
        _uiState.value = UiState(news = usedNews)
        val timer = Timer()
        timer.schedule(5000, 5000){
            updateNews()
        }
    }
}