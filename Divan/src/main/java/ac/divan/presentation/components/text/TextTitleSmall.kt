package ac.divan.presentation.components.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun TextTitleSmall(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onBackground,
    textAlign: TextAlign = TextAlign.Start,
    fontWeight: FontWeight? = MaterialTheme.typography.titleSmall.fontWeight,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE
) {
    TextBase(
        text = text,
        modifier = modifier,
        color = color,
        textAlign = textAlign,
        fontFamily = MaterialTheme.typography.titleSmall.fontFamily,
        fontSize = MaterialTheme.typography.titleSmall.fontSize,
        fontWeight = fontWeight,
        overflow = overflow,
        maxLines = maxLines
    )
}