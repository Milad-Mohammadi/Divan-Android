package ac.divan.presentation.components.text

import ac.divan.util.Constants
import ac.divan.util.extensions.containsUrl
import ac.divan.util.extensions.getAnnotatedString
import ac.divan.util.extensions.openUrl
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun TextBase(
    text: String,
    modifier: Modifier,
    color: Color,
    textAlign: TextAlign,
    fontFamily: FontFamily?,
    fontSize: TextUnit,
    fontWeight: FontWeight?,
    overflow: TextOverflow,
    maxLines: Int,
) {
    val context = LocalContext.current
    val annotatedString = text.getAnnotatedString()
    val containsUrl = text.containsUrl()
    val lineHeight = (fontSize.value + 6).sp

    if (containsUrl) {
        ClickableText(
            text = annotatedString,
            modifier = modifier,
            overflow = overflow,
            maxLines = maxLines,
            style = TextStyle(
                fontFamily = fontFamily,
                fontSize = fontSize,
                fontWeight = fontWeight,
                textAlign = textAlign,
                lineHeight = lineHeight,
                color = color
            ),
            onClick = { offset ->
                val urlAnnotation = annotatedString
                    .getStringAnnotations(
                        tag = Constants.TAG_URL,
                        start = offset,
                        end = offset
                    )
                    .firstOrNull()

                // Only open the URL if the clicked text is a URL
                urlAnnotation?.let { annotation ->
                    context.openUrl(annotation.item)
                }
            }
        )
    } else {
        Text(
            text = text,
            modifier = modifier,
            fontFamily = fontFamily,
            fontSize = fontSize,
            fontWeight = fontWeight,
            textAlign = textAlign,
            color = color,
            overflow = overflow,
            maxLines = maxLines,
            lineHeight = lineHeight
        )
    }
}
