package ac.divan.presentation.home.components

import ac.divan.presentation.components.text.TextBodyLarge
import ac.divan.presentation.components.text.TextBodyMedium
import ac.divan.presentation.components.text.TextTitleMedium
import ac.divan.presentation.components.text.TextTitleSmall
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ProfileCard(
    name: String,
    imageUrl: String,
    knownFor: String,
    title: String,
    segment: String,
    previousCompanies: String,
    country: String,
    additionalInfo: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .border(width = 1.dp, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f), shape = RoundedCornerShape(10.dp))
            .padding(16.dp)
    ) {

        TextTitleMedium(text = name, modifier = Modifier.padding(bottom = 4.dp))
        TextTitleSmall(
            text = title,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        SectionLabel(label = "Known for")
        TextBodyLarge(
            text = knownFor,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        // Section: Segment (with clickable chip)
        SectionLabel(label = "Segment")
        Chip(text = segment, backgroundColor = Color.Red.copy(alpha = 0.1f), textColor = Color.Red)

        Spacer(modifier = Modifier.height(12.dp))

        // Section: Previous Companies
        SectionLabel(label = "Previous companies")
        TextBodyLarge(
            text = previousCompanies.ifEmpty { "No information available" },
            modifier = Modifier.padding(bottom = 12.dp)
        )

        // Section: Country (with clickable chip)
        SectionLabel(label = "Country")
        Chip(text = country, backgroundColor = Color.Blue.copy(alpha = 0.1f), textColor = Color.Blue)

        Spacer(modifier = Modifier.height(12.dp))

        // Section: Additional Info (with clickable link)
        SectionLabel(label = "Additional info")
        TextBodyLarge(text = additionalInfo)
    }
}

@Composable
fun SectionLabel(label: String) {
    TextBodyMedium(
        text = label,
        color = Color.Gray,
        modifier = Modifier.padding(bottom = 4.dp)
    )
}

@Composable
fun Chip(
    text: String,
    backgroundColor: Color,
    textColor: Color,
    onClick: (() -> Unit)? = null
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(backgroundColor)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        TextBodyMedium(
            text = text,
            color = textColor
        )
    }
}
