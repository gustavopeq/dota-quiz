package gustavo.projects.dotaquiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import gustavo.projects.dotaquiz.databinding.FragmentEndGameBinding


class EndGameFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentEndGameBinding>(inflater, R.layout.fragment_end_game, container, false)


        binding.mainMenuBtn.setOnClickListener{findNavController().navigate(R.id.action_endGameFragment_to_titleFragment)}
        binding.playAgainButton.setOnClickListener{findNavController().navigate(R.id.action_endGameFragment_to_tutorialFragment)}

        return binding.root
    }

}
