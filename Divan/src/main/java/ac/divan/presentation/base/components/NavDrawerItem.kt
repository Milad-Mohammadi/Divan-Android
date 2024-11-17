package ac.divan.presentation.base.components

import ac.divan.presentation.components.text.TextBodyMedium
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun NavDrawerItem(
    slug: String,
    icon: String?,
    title: String,
    onSelect: (slug: String) -> Unit,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    NavigationDrawerItem(
        modifier = modifier.padding(horizontal = 8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = NavigationDrawerItemDefaults
            .colors(
                selectedContainerColor = MaterialTheme
                    .colorScheme
                    .primary
                    .copy(alpha = 0.1f)
            ),
        label = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                icon?.let {
                    AsyncImage(
                        model = it,
                        contentDescription = title,
                        colorFilter = ColorFilter.tint(if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground)
                    )
                }

                TextBodyMedium(
                    text = title,
                    color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )
            }
        },
        selected = isSelected,
        onClick = { onSelect(slug) }
    )
}
