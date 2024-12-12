package ac.divan.presentation.home.components

import ac.divan.data.remote.dto.content_pagination.RenderedDataItem
import ac.divan.presentation.components.text.TextBodyMedium
import ac.divan.presentation.components.text.TextTitleMedium
import ac.divan.ui.theme.Dimens
import ac.divan.util.extensions.containsImageUrl
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ProfileCard(
    data: List<RenderedDataItem>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(Dimens.normal))
            .border(
                width = Dimens.extraSmall,
                color = MaterialTheme
                    .colorScheme
                    .onBackground.copy(alpha = 0.3f),
                shape = RoundedCornerShape(Dimens.normal)
            )
            .padding(Dimens.medium)
    ) {
        data.forEach { item ->
            if ((item.value ?: "").containsImageUrl()) {
                AsyncImage(
                    model = item.getRawValue(),
                    contentDescription = item.getRawValue(),
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(Dimens.normal.value.plus(2).dp)),
                    placeholder = null,
                    error = null
                )
            } else if (item.value.isNullOrBlank().not()) {
                SectionLabel(label = item.title)
                TextTitleMedium(
                    text = item.value.toString(),
                    modifier = Modifier.padding(bottom = Dimens.smaller)
                )
            }
        }
    }
}

@Composable
fun SectionLabel(label: String) {
    TextBodyMedium(
        text = label,
        color = Color.Gray,
        modifier = Modifier.padding(bottom = Dimens.smaller)
    )
}