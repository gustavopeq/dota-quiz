package gustavo.projects.dotaquiz.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rank_players_score")
data class PlayerScore(
            @PrimaryKey(autoGenerate = true)
            var playerId: Int = 0,

            @ColumnInfo(name = "player_name")
            var playerName: String,

            @ColumnInfo(name = "player_bestScore")
            var playerBestScore: Int = 0
)