package ac.divan.presentation.base.components

import ac.divan.R
import ac.divan.presentation.components.text.TextBodyMedium
import ac.divan.ui.UiTools
import ac.divan.ui.theme.Dimens
import android.content.Context
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage

@Composable
fun NavDrawerItem(
    context: Context,
    slug: String,
    icon: String?,
    title: String,
    onSelect: (slug: String) -> Unit,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    NavigationDrawerItem(
        modifier = modifier.padding(horizontal = Dimens.normal),
        shape = RoundedCornerShape(Dimens.normal),
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
                horizontalArrangement = Arrangement.spacedBy(Dimens.smaller),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = icon,
                    contentDescription = title,
                    modifier = Modifier
                        .padding(Dimens.normal)
                        .fillMaxWidth(0.1f),
                    colorFilter = ColorFilter.tint(
                        if (isSelected) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.onBackground
                    ),
                    error = painterResource(R.drawable.ic_logo),
                    placeholder = painterResource(R.drawable.ic_logo),
                    imageLoader = UiTools.createSVGImageLoader(context)
                )

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
