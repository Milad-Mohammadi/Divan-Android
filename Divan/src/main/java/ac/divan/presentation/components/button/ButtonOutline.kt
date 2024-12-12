package ac.divan.presentation.components.button

import ac.divan.presentation.components.text.TextBodyLarge
import ac.divan.ui.theme.Dimens
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun ButtonOutline(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.outline,
    contentColor: Color = MaterialTheme.colorScheme.onBackground,
    enabled: Boolean = true,
    icon: Painter? = null,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .border(Dimens.extraSmall, color, RoundedCornerShape(Dimens.normal))
            .clip(RoundedCornerShape(Dimens.normal))
            .clickable { if (enabled) onClick() }
            .padding(
                horizontal = if (text.isNotBlank()) Dimens.large else 13.dp,
                vertical = if (text.isNotBlank()) 12.dp else 13.dp
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (text.isNotBlank()) {
            TextBodyLarge(
                text = text,
                color = contentColor.copy(alpha = if (enabled) 1.0F else 0.6F)
            )
        }

        icon?.let {
            if (text.isNotBlank()) Spacer(modifier = Modifier.width(Dimens.normal))

            Icon(
                painter = it,
                contentDescription = null,
                modifier = modifier.size(Dimens.large),
                tint = contentColor.copy(alpha = if (enabled) 1.0F else 0.6F)
            )
        }
    }
}