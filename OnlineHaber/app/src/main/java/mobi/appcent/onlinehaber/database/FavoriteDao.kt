package mobi.appcent.onlinehaber.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import mobi.appcent.onlinehaber.model.Favorite


@Dao
interface FavoriteDao {

    @Insert
    suspend fun insert(vararg news:Favorite) :List<Long>


    @Query("SELECT * FROM Favorite")
    suspend fun select() : List<Favorite>

    @Query("SELECT * FROM Favorite WHERE uuid= :favoriteId")
    suspend fun selectId(favoriteId : Int) :Favorite

    @Query("DELETE FROM Favorite WHERE uuid= :favoriteId")
    suspend fun delete(favoriteId :Int)
}