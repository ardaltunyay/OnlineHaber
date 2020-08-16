package mobi.appcent.onlinehaber.model

import com.google.gson.annotations.SerializedName

/*
*  TODO Class'ın adı çok genel kaçıyor.
*   Response demek çok genel kaçıyor.
*   Daha özel bir isim verilmeli.
*   Neyle ilgili olduğuna ilk görüşte anlamalıyız.
* */
data class Response(

    @SerializedName("totalResults")
    val totalResults: Int? = null,

    @SerializedName("articles")
    val articles: List<ArticlesItem>? = null,

    @SerializedName("status")
    val status: String? = null
)