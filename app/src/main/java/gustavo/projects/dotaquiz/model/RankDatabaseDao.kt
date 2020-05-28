package gustavo.projects.dotaquiz.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface RankDatabaseDao{

    @Insert
    fun insert(teamInfo: TeamInfo)

    @Update
    fun update(teamInfo: TeamInfo)

    @Query("SELECT * FROM rank_teams_score WHERE teamId = :key")
    fun getById(key: Int): TeamInfo?

    @Query("SELECT * FROM rank_teams_score WHERE team_name = :key")
    fun getByName(key: String): TeamInfo

    // get the top N values. N is passed as parameter
    @Query("SELECT * FROM rank_teams_score ORDER BY team_bestScore desc LIMIT :numberOfElements")
    fun getTopN(numberOfElements: Int): LiveData<List<TeamInfo>>
}