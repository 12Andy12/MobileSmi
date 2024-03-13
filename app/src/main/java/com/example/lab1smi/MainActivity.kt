package com.example.lab1smi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lab1smi.ui.theme.Lab1SmiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab1SmiTheme {
                MainWindow(Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun MainWindow(
    modifier: Modifier = Modifier,
    newsViewModel: ControlViewModel = viewModel()
){
    val uiState by newsViewModel.uiState.collectAsState()
    Column {
        Row(modifier = Modifier.weight(1f)){
            Box(modifier = Modifier
                .weight(1f)
                .border(width = 1.dp, color = Color.LightGray)
            ){
                Node(
                    news = uiState.news[0],
                    onLikeClicked = {uiState.news[0].likes.value++}
                )
            }
            Box(modifier = Modifier
                .weight(1f)
                .border(width = 1.dp, color = Color.LightGray)
            ){
                Node(
                    news = uiState.news[1],
                    onLikeClicked = {uiState.news[1].likes.value++}
                )
            }
        }
        Row(modifier = Modifier.weight(1f)) {
            Box(modifier = Modifier
                .weight(1f)
                .border(width = 1.dp, color = Color.LightGray)
            ){
                Node(
                    news = uiState.news[2],
                    onLikeClicked = {uiState.news[2].likes.value++}
                )
            }
            Box(modifier = Modifier
                .weight(1f)
                .border(width = 1.dp, color = Color.LightGray)
            ){
                Node(
                    news = uiState.news[3],
                    onLikeClicked = {uiState.news[3].likes.value++}
                )
            }
        }
    }
}

@Composable
fun Node(
    modifier: Modifier = Modifier,
    news: NewsData,
    onLikeClicked: () -> Unit
){
    Column {
        Text(text = news.newsText,
            modifier = Modifier

                .weight(9f)
                .background(Color.Gray),
            style = TextStyle(fontSize = 20.sp, color = Color.White)
        )
        Button(onClick = onLikeClicked,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White),
            shape = MaterialTheme.shapes.extraSmall,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Text(text = "Нраица: ${news.likes.value}",
                style = TextStyle(fontSize = 16.sp)
            )
        }
    }
}