package ac.divan.presentation.components.bottom_navigation

import ac.divan.navigation.BottomNavigationItem
import ac.divan.navigation.NavigationBarItems
import ac.divan.presentation.components.text.TextLabelSmall
import ac.divan.ui.Tools.noRippleClickable
import ac.divan.ui.theme.Error
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavigationButton(
    item: BottomNavigationItem,
    showDot: Boolean,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .noRippleClickable { onClick() }
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(modifier = Modifier.padding(4.dp)) {
            Column(
                verticalArrangement = Arrangement.spacedBy(3.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = if (isSelected) item.iconFillId else item.iconId),
                    contentDescription = null,
                    tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground,
                )
                TextLabelSmall(
                    text = item.title,
                    color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )
            }

            if (showDot) {
                Box(
                    modifier = Modifier
                        .size(5.dp)
                        .clip(CircleShape)
                        .background(Error)
                        .align(Alignment.TopEnd)
                )
            }
        }
    }
}


@Preview
@Composable
fun BottomNavigationButtonPrev() {
    BottomNavigationButton(
        item = NavigationBarItems.get(LocalContext.current).first(),
        showDot = false,
        isSelected = true,
        onClick = {}
    )
}