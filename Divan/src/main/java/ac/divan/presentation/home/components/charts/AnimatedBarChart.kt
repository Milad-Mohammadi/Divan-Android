package ac.divan.presentation.home.components.charts

import ac.divan.ui.theme.Dimens
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AnimatedBarChart(
    modifier: Modifier = Modifier,
    data: List<Pair<String, ChartItem>>,
    maxBarHeight: Float = 300f,
    animationDuration: Int = 800
) {
    val maxValue = data.maxOfOrNull { it.second.value } ?: 1f
    val normalizedData = data.map {
        it.first to ChartItem(it.second.value / maxValue * maxBarHeight, it.second.color)
    }

    // Calculate bar width dynamically based on the available screen width
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val barSpacing = Dimens.normal
    val totalBarSpacing = ((data.size - 1) * barSpacing.value).dp // Total spacing between bars
    val barWidth = (screenWidth - totalBarSpacing) / data.size  // Dynamic width for each bar

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Bar Chart
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(maxBarHeight.dp)
                .drawBehind {
                    drawAxisAndGridLines(maxValue)
                }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(maxBarHeight.dp)
                    .padding(horizontal = Dimens.medium),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.spacedBy(0.dp)  // No fixed spacing, dynamic width handles the spacing
            ) {
                normalizedData.forEach { (_, barItem) ->
                    val animatedHeight = remember { Animatable(0f) }
                    LaunchedEffect(key1 = barItem.value) {
                        animatedHeight.animateTo(
                            targetValue = barItem.value,
                            animationSpec = tween(durationMillis = animationDuration)
                        )
                    }

                    Box(
                        modifier = Modifier
                            .height(animatedHeight.value.dp)
                            .width(barWidth)
                            .background(Color(android.graphics.Color.parseColor(barItem.color))),
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(Dimens.medium))

        // Labels
        ChartLabels(data)
    }
}

fun DrawScope.drawAxisAndGridLines(maxValue: Float) {
    val lineColor = Color.Gray.copy(alpha = 0.5f)
    val barChartHeight = size.height

    drawLine(
        color = lineColor,
        start = Offset(0f, 0f),
        end = Offset(0f, barChartHeight),
        strokeWidth = 2f
    )

    for (i in 0..maxValue.toInt()) {
        val yPos = barChartHeight * (i / maxValue)
        drawLine(
            color = lineColor,
            start = Offset(0f, yPos),
            end = Offset(size.width, yPos),
            strokeWidth = 1f
        )

        val axisValue = (maxValue - (maxValue * (i / maxValue))).toInt()
        drawContext.canvas.nativeCanvas.drawText(
            axisValue.toString(),
            Dimens.smaller.toPx(),
            yPos - Dimens.smaller.toPx(),
            android.graphics.Paint().apply {
                textSize = 12.sp.toPx()
                color = lineColor.toArgb()
            }
        )
    }
}
