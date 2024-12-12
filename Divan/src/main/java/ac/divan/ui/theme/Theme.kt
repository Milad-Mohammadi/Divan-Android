package ac.divan.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val colorPalette = darkColorScheme(
    primary = Primary,
    background = White,
    surface = Surface,
    surfaceTint = SurfaceTint,
    onPrimary = OnPrimary,
    onBackground = OnBackground,
    onSurface = OnSurface,
    outline = PlaceHolder
)

@Composable
fun DivanTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = colorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}