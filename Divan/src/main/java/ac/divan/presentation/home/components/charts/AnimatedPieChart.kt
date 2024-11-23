package ac.divan.presentation.home.components.charts

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AnimatedPieChart(
    modifier: Modifier = Modifier,
    data: List<Pair<String, ChartItem>>, // Label and ChartItem
    size: Dp = 200.dp,
    animationDuration: Int = 800
) {
    val totalValue = data.sumOf { it.second.value.toInt() }
    val angleList = data.map { (it.second.value / totalValue) * 360f }

    // Create a list of start and end angles for each slice
    val angles = angleList.scan(0f) { acc, angle -> acc + angle }

    Box(
        modifier = modifier
            .size(size)
            .pointerInput(Unit) {
                detectTapGestures {
                    // Handle tap gestures if needed (e.g., to display more info)
                }
            },
        contentAlignment = Alignment.Center
    ) {
        // Draw the pie chart slices with animation
        Canvas(modifier = Modifier.size(size)) {
            angles.forEachIndexed { index, endAngle ->
                val finalIndex = if (index == 0) 0 else index - 1
                val startAngle = if (index == 0) 0f else angles[index - 1]

                // Draw a slice for each section of the pie
                val radius = size.toPx() / 2f
                val sweepAngle = endAngle - startAngle

                drawArc(
                    color = Color(android.graphics.Color.parseColor(data[finalIndex].second.color)),
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = true,
                    topLeft = center.copy(x = size.toPx() / 2f, y = size.toPx() / 2f),
                    size = androidx.compose.ui.geometry.Size(radius * 2, radius * 2)
                )
            }
        }

        // Optionally, add the label inside the pie
        PieChartLabels(data)
    }
}

@Composable
fun PieChartLabels(data: List<Pair<String, ChartItem>>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
    ) {
        data.forEach { (label, chartItem) ->
            Text(
                text = "$label: ${chartItem.value.toInt()}",
                style = TextStyle(fontSize = 12.sp),
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
    }
}
