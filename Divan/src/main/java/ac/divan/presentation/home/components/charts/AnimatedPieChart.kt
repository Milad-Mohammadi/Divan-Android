package ac.divan.presentation.home.components.charts

import ac.divan.presentation.components.text.TextBodyMedium
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedPieChart(
    modifier: Modifier = Modifier,
    data: List<Pair<String, ChartItem>>,
    chartBarWidth: Dp = 35.dp,
    animDuration: Int = 800
) {
    val labelsWidth by remember { mutableIntStateOf(0) }
    val deviceWidth = LocalConfiguration.current.screenWidthDp.dp
    val density = LocalDensity.current

    val totalSum = data.sumOf { it.second.value.toInt() }
    val floatValue = mutableListOf<Float>()

    data.forEachIndexed { index, pair ->
        floatValue.add(index, 360 * pair.second.value / totalSum.toFloat())
    }

    var animationPlayed by remember { mutableStateOf(false) }

    var lastValue = 0f

    val chartSize = with(density) { (deviceWidth.value - labelsWidth).toDp() }

    val animateSize by animateFloatAsState(
        targetValue = if (animationPlayed) chartSize.value else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = 0,
            easing = LinearOutSlowInEasing
        ), label = ""
    )

    val animateRotation by animateFloatAsState(
        targetValue = if (animationPlayed) 90f * 11f else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = 0,
            easing = LinearOutSlowInEasing
        ), label = ""
    )

    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        DetailsPieChart(data = data)

        Box(
            modifier = Modifier.size(animateSize.dp).aspectRatio(1f),
            contentAlignment = Alignment.Center
        ) {
            Canvas(
                modifier = Modifier
                    .offset { IntOffset.Zero }
                    .size(chartSize)
                    .rotate(animateRotation)
            ) {
                floatValue.forEachIndexed { index, value ->
                    drawArc(
                        color = Color(android.graphics.Color.parseColor(data[index].second.color)),
                        lastValue,
                        value,
                        useCenter = false,
                        style = Stroke(chartBarWidth.toPx(), cap = StrokeCap.Butt)
                    )
                    lastValue += value
                }
            }
        }
    }
}


@Composable
fun DetailsPieChart(
    data: List<Pair<String, ChartItem>>,
) {
    Column {
        data.sortedBy { it.first }.forEachIndexed { _, value ->
            DetailsPieChartItem(
                data = Pair(value.first, value.second.value.toInt()),
                color = Color(android.graphics.Color.parseColor(value.second.color))
            )
        }
    }
}

@Composable
fun DetailsPieChartItem(
    data: Pair<String, Int>,
    height: Dp = 16.dp,
    color: Color
) {

    if (data.second > 0) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .background(
                        color = color,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .size(height)
            )

            Column(modifier = Modifier.padding(start = 15.dp)) {
                TextBodyMedium(
                    text = data.first,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                TextBodyMedium(
                    text = data.second.toString(),
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                )
            }
        }
    }
}