package ac.divan.presentation.home.components

import ac.divan.data.remote.dto.content_pagination.RenderedDataItem
import ac.divan.presentation.components.text.TextBodyMedium
import ac.divan.presentation.components.text.TextTitleMedium
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ProfileCard(
    data: List<RenderedDataItem>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .border(width = 1.dp, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f), shape = RoundedCornerShape(10.dp))
            .padding(16.dp)
    ) {
        data.forEach { item ->
            if (item.value.isNullOrBlank().not()) {
                SectionLabel(label = item.title)
                TextTitleMedium(text = item.value.toString(), modifier = Modifier.padding(bottom = 4.dp))
            }
        }
    }
}

@Composable
fun SectionLabel(label: String) {
    TextBodyMedium(
        text = label,
        color = Color.Gray,
        modifier = Modifier.padding(bottom = 4.dp)
    )
}