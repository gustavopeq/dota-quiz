package gustavo.projects.dotaquiz.pregame.teamSelection

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController

import gustavo.projects.dotaquiz.R
import gustavo.projects.dotaquiz.databinding.TeamSelectionFragmentBinding
import gustavo.projects.dotaquiz.model.RankDatabase

class TeamSelection : Fragment() {

    private lateinit var viewModel: TeamSelectionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<TeamSelectionFragmentBinding>(inflater, R.layout.team_selection_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val database = RankDatabase.getInstance(application).rankDatabaseDao

        val viewModelFactory = TeamSelectionViewModelFactory(database)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TeamSelectionViewModel::class.java)

        binding.confirmBtn.isEnabled = false

        binding.teamNameEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // Nothing to implement
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Nothing to implement
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.confirmBtn.isEnabled = !s.isNullOrBlank()
            }
        })

        binding.confirmBtn.setOnClickListener { viewModel.createNewTeam(binding.teamNameEditText.text.toString())  }

        viewModel.canStartGame.observe(viewLifecycleOwner, Observer<Boolean> { canStartGame ->
            if(canStartGame) startGame()
        })


        displayExistingTeamDialog("Test", 100)

        return binding.root
    }

    private fun startGame() {

        val action = TeamSelectionDirections.actionTeamSelectionToGameFragment()
        action.teamName = viewModel.teamSelectedName

        NavHostFragment.findNavController(this).navigate(action)
    }

    private fun displayExistingTeamDialog(teamName: String, teamBestScore: Int) {

        val builder = AlertDialog.Builder(this.activity)
        builder.setTitle("Existent Team!")
        builder.setMessage("The team $teamName already exist and has $teamBestScore points. Do you want to use this team or choose another?")
        builder.setPositiveButton("Use this") { dialog: DialogInterface?, which: Int ->
            // nothing
        }

        builder.setNegativeButton("Choose another") { dialog: DialogInterface?, which: Int ->
            // nothing
        }

        builder.show()
    }

}
