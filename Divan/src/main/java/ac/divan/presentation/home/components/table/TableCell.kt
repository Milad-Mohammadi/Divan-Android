package ac.divan.presentation.home.components.table

import ac.divan.data.remote.dto.content_pagination.RenderedDataItem
import ac.divan.presentation.components.text.TextBodyMedium
import ac.divan.util.extensions.containsImageUrl
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage


@Composable
fun TableCell(item: RenderedDataItem, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.padding(8.dp)
    ) {
        if ((item.value ?: "").containsImageUrl()) {
            AsyncImage(
                model = item.getRawValue(),
                contentDescription = item.getRawValue(),
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .height(30.dp)
                    .clip(RoundedCornerShape(4.dp)),
                placeholder = null,
                error = null
            )
        } else if (item.value.isNullOrBlank().not()) {
            TextBodyMedium(text = item.value ?: "")
        }
    }
}