package mobi.appcent.onlinehaber.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import mobi.appcent.onlinehaber.model.ArticlesItem

@Dao
interface NewsDao {
    //ekleme işlemleri burada yapılacak
    @Insert
    suspend fun insertAll(vararg news:ArticlesItem) :List<Long>


    @Query("SELECT * FROM ArticlesItem")
    suspend fun getAllNews() : List<ArticlesItem>

    @Query("SELECT * FROM ArticlesItem WHERE uuid= :newsId")
    suspend fun getNews(newsId : Int) :ArticlesItem

    @Query("DELETE FROM ArticlesItem")
    suspend fun deleteAllNews()
}