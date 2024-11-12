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
    onSecondary = OnSecondary,
    onBackground = OnBackground,
    onSurface = OnSurface,
    onSurfaceVariant = Body,
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