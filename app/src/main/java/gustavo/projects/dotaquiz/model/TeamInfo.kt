package gustavo.projects.dotaquiz.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "rank_teams_score", indices = [Index(value = ["team_name"], unique = true)])
data class TeamInfo(
    @PrimaryKey(autoGenerate = true)
            var teamId: Int = 0,

    @ColumnInfo(name = "team_name")
            var teamName: String,

    @ColumnInfo(name = "team_bestScore")
            var teamBestScore: Int = 0
)