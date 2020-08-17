package mobi.appcent.onlinehaber.model

import com.google.gson.annotations.SerializedName

data class GetNewsResponse(

    @SerializedName("totalResults")
    val totalResults: Int? = null,

    @SerializedName("articles")
    val articles: List<ArticlesItem>? = null,

    @SerializedName("status")
    val status: String? = null
)