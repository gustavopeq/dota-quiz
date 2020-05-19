package gustavo.projects.dotaquiz.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface RankDatabaseDao{

    @Insert
    fun insert(playerScore: PlayerScore)

    @Update
    fun update(playerScore: PlayerScore)

    @Query("SELECT * FROM rank_players_score WHERE playerId = :key")
    fun getById(key: Int): PlayerScore?

    @Query("SELECT * FROM rank_players_score WHERE player_name = :key")
    fun getByName(key: String): PlayerScore

    @Query("SELECT * FROM rank_players_score ORDER BY player_bestScore LIMIT 5")
    fun getTop5(): Array<PlayerScore>
}