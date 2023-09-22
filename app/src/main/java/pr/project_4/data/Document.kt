package pr.project_4.data

import com.google.gson.annotations.SerializedName

data class Document(
    @SerializedName("collection")
    val collection: String,
    @SerializedName("datetime")
    val datetime: String,
    @SerializedName("display_sitename")
    val displaySitename: String,
    @SerializedName("doc_url")
    val docUrl: String? = null, // 기본값으로 null을 제공
    @SerializedName("height")
    val height: String? = null, // 기본값으로 null을 제공
    @SerializedName("image_url")
    val imageUrl: String? = null, // 기본값으로 null을 제공
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String, // 기본값으로 null을 제공
    @SerializedName("width")
    val width: Int? = null, // 기본값으로 null을 제공
    var url: String,
)
