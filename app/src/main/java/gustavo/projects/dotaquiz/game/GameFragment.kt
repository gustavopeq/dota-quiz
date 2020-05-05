package gustavo.projects.dotaquiz.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
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

        binding.wrongBtn.setOnClickListener{findNavController().navigate(R.id.action_gameFragment_to_endGameFragment)}

        return binding.root
    }
}
