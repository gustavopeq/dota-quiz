package gustavo.projects.dotaquiz.rank

import android.util.Log
import androidx.lifecycle.ViewModel
import gustavo.projects.dotaquiz.model.RankDatabaseDao

class RankFragmentViewModel(val dataSource: RankDatabaseDao) : ViewModel() {

    init{
        Log.i("Print", "Initialized")
    }
}
