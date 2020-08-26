package mobi.appcent.onlinehaber.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favorite(
    @ColumnInfo(name = "publishedAt")
    var publishedAt: String? = null,

    @ColumnInfo(name = "author")
    var author: String? = null,

    @ColumnInfo(name = "urlToImage")
    var urlToImage: String? = null,

    @ColumnInfo(name = "description")
    var description: String? = null,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "url")
    var url: String? = null,

    @ColumnInfo(name = "content")
    var content: String? = null
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}