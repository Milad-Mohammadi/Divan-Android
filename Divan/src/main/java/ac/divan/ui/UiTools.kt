package ac.divan.ui

import android.content.Context
import coil.ImageLoader
import coil.decode.SvgDecoder

object UiTools {
    fun createSVGImageLoader(context: Context): ImageLoader {
        return ImageLoader.Builder(context)
            .components { add(SvgDecoder.Factory()) }
            .build()
    }
}