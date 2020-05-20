package gustavo.projects.dotaquiz.pregame.teamSelection

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import gustavo.projects.dotaquiz.R
import gustavo.projects.dotaquiz.databinding.TeamSelectionFragmentBinding

class TeamSelection : Fragment() {

    private lateinit var viewModel: TeamSelectionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<TeamSelectionFragmentBinding>(inflater, R.layout.team_selection_fragment, container, false)

        return binding.root
    }

}
