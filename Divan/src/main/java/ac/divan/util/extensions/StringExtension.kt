package ac.divan.util.extensions

import ac.divan.util.Constants
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle


@Composable
fun String.getAnnotatedString(): AnnotatedString {
    val text = this

    return buildAnnotatedString {
        val urlRegex = "(https?://\\S+)".toRegex()
        var lastIndex = 0

        urlRegex.findAll(text).forEach { matchResult ->
            val url = matchResult.value
            val baseUrl = url.removePrefix("https://").removePrefix("http://").removeSuffix("/")

            val start = matchResult.range.first
            val end = matchResult.range.last + 1

            // Add regular text before the URL
            append(text.substring(lastIndex, start))

            // Add clickable URL
            pushStringAnnotation(
                tag = Constants.TAG_URL,
                annotation = url
            )
            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colorScheme.primary,
                    textDecoration = TextDecoration.Underline
                )
            ) {
                append(baseUrl) // Use the base URL without the prefix
            }
            pop()
            lastIndex = end
        }

        // Add remaining text
        if (lastIndex < text.length) {
            append(text.substring(lastIndex))
        }
    }
}

fun String.containsUrl(): Boolean {
    val urlRegex = "(https?://\\S+)".toRegex()
    return urlRegex.containsMatchIn(this)
}

fun String.containsImageUrl(): Boolean {
    val fileUrlRegex = "(https?://\\S+\\.(jpg|jpeg|png|gif|bmp|webp|svg))".toRegex(RegexOption.IGNORE_CASE)
    return fileUrlRegex.containsMatchIn(this)
}
