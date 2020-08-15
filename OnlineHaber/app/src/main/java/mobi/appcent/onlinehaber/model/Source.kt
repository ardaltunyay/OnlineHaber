package mobi.appcent.onlinehaber.model

import com.google.gson.annotations.SerializedName

//TODO Class'ın adı doğru değil gibi geldi
data class Source(

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("id")
    val id: String? = null
)