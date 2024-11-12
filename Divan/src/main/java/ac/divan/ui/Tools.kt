package ac.divan.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

object Tools {
    @SuppressLint("ModifierFactoryUnreferencedReceiver")
    fun Modifier.noRippleClickable(action: () -> Unit) = composed {
        clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() } // This is mandatory
        ) {
            action()
        }
    }
}