package ac.divan.presentation.home.components.charts

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.round
import kotlin.math.sin

@Composable
fun AnimatedPieChart(
    modifier: Modifier = Modifier,
    data: List<Pair<String, ChartItem>>,
    animDuration: Int = 500,
    showPercent: Boolean = false
) {
    val primary = MaterialTheme.colors.primary
    val density = LocalDensity.current
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    val totalSum = data.sumOf { it.second.value.toDouble() }.toFloat()
    val floatValue = data.map { (it.second.value / totalSum) * 360f }

    var animationPlayed by remember { mutableStateOf(false) }
    var lastValue = 0f

    val animateSize by animateFloatAsState(
        targetValue = if (animationPlayed) with(density) { screenWidth.toPx() - 16 } else 0f,
        animationSpec = tween(animDuration, easing = LinearOutSlowInEasing), label = ""
    )

    val animateRotation by animateFloatAsState(
        targetValue = if (animationPlayed) 360f else 0f,
        animationSpec = tween(animDuration, easing = LinearOutSlowInEasing), label = ""
    )

    LaunchedEffect(true) {
        animationPlayed = true
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Chart
        Box(
            modifier = Modifier
                .size(with(density) { animateSize.toDp() })
                .aspectRatio(1f),
            contentAlignment = Alignment.Center
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .offset { IntOffset.Zero }
                    .rotate(animateRotation)
            ) {
                floatValue.forEachIndexed { index, value ->
                    if (value > 0f) {
                        drawArc(
                            color = if (data[index].second.color.isBlank()) primary
                            else Color(android.graphics.Color.parseColor(data[index].second.color)),
                            startAngle = lastValue,
                            sweepAngle = value,
                            useCenter = true
                        )
                        val angle = Math.toRadians((lastValue + value / 2).toDouble())
                        val x = center.x + (size.width / 3) * cos(angle)
                        val y = center.y + (size.height / 3) * sin(angle)
                        val count = data[index].second.value.toInt()
                        val percent = round(data[index].second.value / totalSum * 100).toInt()
                        drawContext.canvas.nativeCanvas.apply {
                            drawText(
                                if (showPercent) {
                                    "$percent%"
                                } else {
                                    count.toString()
                                },
                                x.toFloat(),
                                y.toFloat(),
                                android.graphics.Paint().apply {
                                    color = android.graphics.Color.BLACK
                                    textSize = size.minDimension / 25
                                    textAlign = android.graphics.Paint.Align.CENTER
                                }
                            )
                        }
                        lastValue += value
                    }
                }
            }
        }

        ChartLabels(data)
    }
}

