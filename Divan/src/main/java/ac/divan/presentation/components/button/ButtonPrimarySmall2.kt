package ac.divan.presentation.components.button

import ac.divan.presentation.components.text.TextBodySmall
import ac.divan.ui.theme.Dimens
import androidx.compose.foundation.background
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
fun ButtonPrimarySmall2(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    color: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    icon: Painter? = null,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(Dimens.normal))
            .background(color.copy(alpha = if (enabled) 1.0F else 0.6F))
            .clickable { if (enabled) onClick() }
            .padding(horizontal = 12.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (text.isNotBlank()) {
            TextBodySmall(
                text = text,
                color = contentColor.copy(alpha = if (enabled) 1.0F else 0.6F)
            )
        }

        icon?.let {
            if (text.isNotBlank()) Spacer(modifier = Modifier.width(Dimens.smaller))

            Icon(
                painter = it,
                contentDescription = null,
                modifier = Modifier.size(12.dp),
                tint = contentColor.copy(alpha = if (enabled) 1.0F else 0.6F)
            )
        }
    }
}