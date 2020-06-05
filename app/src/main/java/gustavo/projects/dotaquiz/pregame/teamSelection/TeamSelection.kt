package gustavo.projects.dotaquiz.pregame.teamSelection

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment

import gustavo.projects.dotaquiz.R
import gustavo.projects.dotaquiz.databinding.TeamSelectionFragmentBinding
import gustavo.projects.dotaquiz.model.RankDatabase
import gustavo.projects.dotaquiz.model.TeamInfo

class TeamSelection : Fragment() {

    private lateinit var viewModel: TeamSelectionViewModel
    private lateinit var binding: TeamSelectionFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.team_selection_fragment, container, false)

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

        viewModel.canShowExistentTeamDialog.observe(viewLifecycleOwner, Observer<Boolean> { canShowDialog ->
            if(canShowDialog) showExistingTeamDialog(viewModel.teamSelected)
        })

        return binding.root
    }

    private fun showExistingTeamDialog(teamInfo: TeamInfo) {

        val builder = AlertDialog.Builder(this.activity)
        builder.setTitle("Existent Team!")
        builder.setMessage("The team ${teamInfo.teamName} already exist and has ${teamInfo.teamBestScore} points. Do you want to use this team or choose another?")
        builder.setPositiveButton("Use this") { dialog: DialogInterface?, which: Int ->
            startGame()
        }

        builder.setNegativeButton("Choose another") { dialog: DialogInterface?, which: Int ->
            closeExistingTeamDialog()
        }

        builder.show()
    }

    private fun startGame() {

        val action = TeamSelectionDirections.actionTeamSelectionToGameFragment()
        action.teamName = viewModel.teamSelected.teamName

        NavHostFragment.findNavController(this).navigate(action)
    }

    private fun closeExistingTeamDialog() = binding.teamNameEditText.text.clear()


}
