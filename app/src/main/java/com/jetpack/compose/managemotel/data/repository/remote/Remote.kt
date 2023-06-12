package com.jetpack.compose.managemotel.data.repository.remote

import com.jetpack.compose.managemotel.data.model.Room

interface Remote {
    suspend fun getData(): List<Room>
}