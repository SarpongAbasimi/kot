package com.todo.ui.thoughtscreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.todo.ui.theme.TodoTheme
import java.time.OffsetDateTime

@Composable
fun ThoughtScreen(
    modifier: Modifier = Modifier,
    thoughtsViewModel: ThoughtsViewModel =  viewModel(factory = ThoughtsViewModel.Factory),
    navigateEdit: (id: String) -> Unit = {}
){

    val state = thoughtsViewModel.uiState.collectAsState().value

    if(state.isEmpty()){
        Text(
            "You currently don't have any thoughts",
            color = Color.Black,
            modifier = modifier
        )
    } else {
        LazyColumn(modifier = modifier) {
            items(items = state, key = {it.id}) { thoughts ->
                ThoughtCard(
                    thought = thoughts.content,
                    createdAt = thoughts.createdAt,
                    handleEditClick =  {navigateEdit(thoughts.id.toString()) }
                )

            }
        }
    }
}

@Composable
private fun ThoughtCard(
    modifier: Modifier = Modifier,
    thought: String,
    createdAt: String,
    handleEditClick: () -> Unit
){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(2, 77, 223),
        ),
        modifier = modifier.height(
            height = 100.dp
        ).fillMaxWidth().padding(10.dp),
        shape = CardDefaults.elevatedShape
    ) {
        Column(verticalArrangement = Arrangement.Center,
            modifier = modifier.padding(5.dp)
        )
        {
            Text(
                thought,
                color = Color.White,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
               modifier =  Modifier.padding(5.dp, 5.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    createdAt,
                    fontSize = 14.sp,
                    color = Color.White,
                    fontFamily = FontFamily.Serif,
                    modifier =  Modifier.padding(5.dp, 5.dp)
                )

                Icon(
                    Icons.Filled.Edit,
                    contentDescription = "Localized description",
                    Modifier.size(20.dp).clickable(onClick = handleEditClick),
                    tint = Color.White
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun ThoughtsPreview(modifier: Modifier = Modifier){
    TodoTheme {
        ThoughtCard(
            thought = "I am thinking of learning how to code",
            createdAt =  "${OffsetDateTime.now()}",
            handleEditClick = {}
        )
    }
}