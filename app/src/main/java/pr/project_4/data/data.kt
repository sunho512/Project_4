package pr.project_4.data


import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("documents")
    val documents: List<Document>,
    @SerializedName("meta")
    val meta: Meta
)