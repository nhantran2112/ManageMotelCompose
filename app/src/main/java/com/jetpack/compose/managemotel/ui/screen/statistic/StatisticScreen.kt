package com.jetpack.compose.managemotel.ui.screen.statistic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.github.tehras.charts.line.LineChart
import com.github.tehras.charts.line.LineChartData
import com.github.tehras.charts.line.renderer.line.SolidLineDrawer
import com.github.tehras.charts.line.renderer.point.FilledCircularPointDrawer
import com.github.tehras.charts.line.renderer.xaxis.SimpleXAxisDrawer
import com.github.tehras.charts.line.renderer.yaxis.SimpleYAxisDrawer
import com.github.tehras.charts.piechart.animation.simpleChartAnimation
import com.jetpack.compose.managemotel.R
import com.jetpack.compose.managemotel.data.model.InfoUtil
import com.jetpack.compose.managemotel.data.model.getTempDataElectronic

@Composable
fun StatisticScreen() {
//    Column {
    YearNavigation()
//        MyLineChartParent()
//    }
}

@Composable
private fun YearNavigation() {
    val electronicYear = getTempDataElectronic().groupBy { it.year }
    val yearList = mutableListOf<List<InfoUtil>>()
    for (year in electronicYear.keys.sorted()) {
        yearList.add(electronicYear[year]?.sortedBy { it.month } ?: emptyList())
    }
    var curPos by remember { mutableIntStateOf(0) }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                if (curPos > 0) {
                    curPos -= 1
                }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = ""
                )
            }
            Text(
                text = yearList[curPos][0].year.toString(),
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            IconButton(onClick = {
                if (curPos < yearList.size) {
                    curPos += 1
                }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_forward),
                    contentDescription = ""
                )
            }
        }
        MyLineChartParent(infoUtilNumber = yearList[curPos])
    }


}

@Composable
private fun MyLineChartParent(infoUtilNumber: List<InfoUtil>) {
    Column(modifier = Modifier.padding(4.dp)) {
        Text(modifier = Modifier.padding(start = 24.dp), text = "kWh")
        Row() {
            LineChart(
                linesChartData = listOf(
                    LineChartData(
                        points = infoUtilNumber.map { item ->
                            LineChartData.Point(
                                item.electronicNumber.toFloat(),
                                item.month.toString()
                            )
                        }, lineDrawer = SolidLineDrawer(), startAtZero = true
                    )
                ),
                // Optional properties.
                modifier = Modifier
                    .weight(1f)
                    .height(300.dp)
                    .padding(all = 4.dp),
                animation = simpleChartAnimation(),
                pointDrawer = FilledCircularPointDrawer(),
                xAxisDrawer = SimpleXAxisDrawer(),
                yAxisDrawer = SimpleYAxisDrawer(),
                horizontalOffset = 5f,
                labels = infoUtilNumber.map { item ->
                    item.month.toString()
                },
            )
            Text(
                modifier = Modifier
                    .align(alignment = Alignment.Bottom)
                    .padding(bottom = 12.dp),
                text = "Month"
            )
        }
    }
}
