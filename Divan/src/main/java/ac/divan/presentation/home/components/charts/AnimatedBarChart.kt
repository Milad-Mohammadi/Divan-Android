package ac.divan.presentation.home.components.charts

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom
    ) {
        Column(
            modifier = Modifier.height(maxBarHeight.dp).align(Alignment.Top),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            for (i in maxValue.toInt() downTo 0) {
                Text(
                    text = i.toString(),
                    fontSize = 12.sp
                )
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Bar Chart
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            normalizedData.forEach { (label, barItem) ->
                val animatedHeight = remember { Animatable(0f) }
                LaunchedEffect(key1 = barItem.value) {
                    animatedHeight.animateTo(
                        targetValue = barItem.value,
                        animationSpec = tween(durationMillis = animationDuration)
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    // Bar
                    Box(
                        modifier = Modifier
                            .height(animatedHeight.value.dp)
                            .width(30.dp)
                            .background(Color(android.graphics.Color.parseColor(barItem.color))),
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    // Label
                    Text(
                        text = label,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}
