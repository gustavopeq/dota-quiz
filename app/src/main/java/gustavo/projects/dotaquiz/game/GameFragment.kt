package gustavo.projects.dotaquiz.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import gustavo.projects.dotaquiz.R
import gustavo.projects.dotaquiz.databinding.FragmentGameBinding


class GameFragment : Fragment() {

    private lateinit var viewModel: GameFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentGameBinding>(inflater,
            R.layout.fragment_game,container,false)

        viewModel = ViewModelProviders.of(this).get(GameFragmentViewModel::class.java)
        binding.gameViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.listOfHeroesEmpty.observe(viewLifecycleOwner, Observer<Boolean> {isEmpty ->
            if(isEmpty){
                Toast.makeText(context, "There is no more heroes :(", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.eventGameFinish.observe(viewLifecycleOwner, Observer<Boolean> {hasFinished ->
            if(hasFinished) gameFinished()
        })

        return binding.root
    }

    private fun gameFinished(){
        viewModel.onGameFinishComplete()

        val action = GameFragmentDirections.actionGameFragmentToEndGameFragment()
        action.finalScore = viewModel.score.value?:0

        NavHostFragment.findNavController(this).navigate(action)

    }
}
