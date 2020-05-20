package gustavo.projects.dotaquiz.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PlayerScore::class], version = 2, exportSchema = false)
public abstract class RankDatabase : RoomDatabase() {

    abstract val rankDatabaseDao: RankDatabaseDao

    // The companion object allows clients to access the method for creating or getting the database without instantiating the class
    companion object{

        // This annotation @Volatile will prevent the variable to be cached. It helps make sure that the value of INSTANCE will be always up-to-date
        @Volatile
        private var INSTANCE: RankDatabase? = null

        fun getInstance(context: Context): RankDatabase {

            // Using synchronized, we make sure that only one thread of execution at a time can enter this block of code, making sure the database only gets initialized once.
            // Even though this is unlikely to happen in this app, its good to keep good practices
            synchronized(this) {
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(context.applicationContext,
                                                    RankDatabase::class.java,
                                                    "rank_players_score").fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}