package mobi.appcent.onlinehaber.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import mobi.appcent.onlinehaber.model.ArticlesItem
import mobi.appcent.onlinehaber.model.Favorite

@Database(entities = arrayOf(ArticlesItem::class, Favorite::class), version = 1)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        @Volatile
        private var instance: NewsDatabase? = null
        private val lock = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(lock)
        {
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            NewsDatabase::class.java,
            "newsDatabase"
        ).build()
    }
}