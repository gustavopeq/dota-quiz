package gustavo.projects.dotaquiz.endgame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import gustavo.projects.dotaquiz.R
import gustavo.projects.dotaquiz.databinding.FragmentEndGameBinding


class EndGameFragment : Fragment() {

    lateinit var viewModel: EndGameViewModel
    lateinit var viewModelFactory: EndGameViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentEndGameBinding>(inflater,
            R.layout.fragment_end_game, container, false)

        viewModelFactory = EndGameViewModelFactory(EndGameFragmentArgs.fromBundle(arguments!!).finalScore)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(EndGameViewModel::class.java)

        binding.endGameViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.destinationID.observe(viewLifecycleOwner, Observer<Int> {destinationID ->
            when(destinationID){
                1 -> navigateToMainMenu()
                2 -> navigateToPreGame()
            }
        })

        return binding.root
    }

    private fun navigateToMainMenu() {
        viewModel.onDestinationChangeComplete()
        NavHostFragment.findNavController(this).navigate(EndGameFragmentDirections.actionEndGameFragmentToTitleFragment())
    }

    private fun navigateToPreGame() {
        viewModel.onDestinationChangeComplete()
        NavHostFragment.findNavController(this).navigate(EndGameFragmentDirections.actionEndGameFragmentToTutorialFragment())
    }

}
