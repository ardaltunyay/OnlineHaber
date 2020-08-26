package mobi.appcent.onlinehaber.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class ArticlesItem(

    @ColumnInfo(name = "publishedAt")
    @SerializedName("publishedAt")
    val publishedAt: String? = null,

    @ColumnInfo(name = "author")
    @SerializedName("author")
    val author: String? = null,

    @ColumnInfo(name = "urlToImage")
    @SerializedName("urlToImage")
    val urlToImage: String? = null,

    @ColumnInfo(name = "description")
    @SerializedName("description")
    val description: String? = null,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String? = null,

    @ColumnInfo(name = "url")
    @SerializedName("url")
    val url: String? = null,

    @ColumnInfo(name = "content")
    @SerializedName("content")
    val content: String? = null
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}