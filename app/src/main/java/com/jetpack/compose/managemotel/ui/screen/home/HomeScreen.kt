package com.jetpack.compose.managemotel.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jetpack.compose.managemotel.R
import com.jetpack.compose.managemotel.data.model.Room
import com.jetpack.compose.managemotel.data.model.initTempData
import com.jetpack.compose.managemotel.data.repository.remote.RemoteImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    ListRoom(
        rooms = initTempData(),
    )
    Box(modifier = Modifier.fillMaxSize()) {
        FloatingActionButton(
            onClick = {},
            backgroundColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .align(alignment = Alignment.BottomEnd)
                .padding(12.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_add),
                contentDescription = "",
                tint = Color.White
            )
        }
    }
}

@Composable
private fun ListRoom(rooms: List<Room>) {
    LazyColumn(modifier = Modifier.padding(vertical = 8.dp)) {
        items(items = rooms) { room ->
            ItemRoom(room = room)
        }
    }
}

@Composable
private fun ItemRoom(room: Room) {
    //use "remember" value not reset when update compose
    var expanded by remember { mutableStateOf(false) }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier.padding(8.dp),
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Room number : ${room.room}")
                Text(
                    text = "Detail : ${room.detail}",
                    maxLines = if (expanded) Int.MAX_VALUE else 1
                )
            }
            IconButton(onClick = {
                expanded = !expanded
                CoroutineScope(Dispatchers.Default).launch {
                    RemoteImpl().getData()
                }
            }) {
                Icon(
                    painter =
                    if (expanded) painterResource(id = R.drawable.ic_arrow_up)
                    else painterResource(id = R.drawable.ic_arrow_down),
                    contentDescription = if (expanded) "Show less" else "Show more"
                )
            }
        }
    }
}


