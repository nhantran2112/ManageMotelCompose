package com.jetpack.compose.managemotel.data.model

const val PRICE_ELECTRONIC = 3500 //3k5/kWh
const val PRICE_WATER = 7000 //7k/m^3
const val PRICE_WATER_DEFAULT = 40000 //40k/person

class InfoUtil(
    oldElectronic: Int,
    curElectronic: Int,
    oldWater: Int,
    curWater: Int,
    val month: Int,
    val year: Int
) {
    val electronicNumber = curElectronic - oldElectronic
    val electronicBill = electronicNumber * PRICE_ELECTRONIC

    val waterNumber = curWater - oldWater
    val waterBill = waterNumber * PRICE_WATER
}

fun getTempDataElectronic(): List<InfoUtil> {
    return listOf(
        InfoUtil(100, 120, 0, 0, 7, 2022),
        InfoUtil(120, 130, 0, 0, 8, 2022),
        InfoUtil(130, 145, 0, 0, 9, 2022),
        InfoUtil(145, 178, 0, 0, 10, 2022),
        InfoUtil(178, 200, 0, 0, 11, 2022),
        InfoUtil(200, 228, 0, 0, 12, 2022),
        InfoUtil(228, 240, 0, 0, 1, 2023),

        InfoUtil(285, 300, 0, 0, 4, 2023),
        InfoUtil(328, 350, 0, 0, 5, 2023),
        InfoUtil(350, 370, 0, 0, 6, 2023),
        InfoUtil(240, 260, 0, 0, 2, 2023),
        InfoUtil(260, 285, 0, 0, 3, 2023),
    )
}

