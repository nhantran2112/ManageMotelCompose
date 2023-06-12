package com.jetpack.compose.managemotel.data.repository.remote

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.jetpack.compose.managemotel.data.model.Room

class RemoteImpl : Remote {
    private val database = Firebase.database.reference.child("data")

    override suspend fun getData(): List<Room> {
        database.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.value
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
        return emptyList()
    }
}