package com.jetpack.compose.managemotel.data.model

data class Room(
    var room: Int,
    var detail: String,
    var bill: List<InfoUtil>,
    var people: Int
)

fun initTempData(): List<Room> {
    return listOf(
        Room(
            1,
            "The bar chart below give information about five countries spending habits of shopping on consumer goods in 2012.",
            getTempDataElectronic(), 2
        )
    )
}