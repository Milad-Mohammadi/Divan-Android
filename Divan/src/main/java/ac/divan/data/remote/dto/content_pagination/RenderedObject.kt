package ac.divan.data.remote.dto.content_pagination

import com.google.gson.annotations.SerializedName

data class RenderedObject(
    @SerializedName("rendered_data") val renderedData: Map<String, RenderedDataItem>
)