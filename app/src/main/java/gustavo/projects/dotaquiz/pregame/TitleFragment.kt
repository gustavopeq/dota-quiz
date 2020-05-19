package gustavo.projects.dotaquiz.pregame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import gustavo.projects.dotaquiz.R
import gustavo.projects.dotaquiz.databinding.FragmentTitleBinding


class TitleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentTitleBinding>(inflater,
            R.layout.fragment_title, container, false)

        binding.startGameBtn.setOnClickListener{findNavController().navigate(R.id.action_titleFragment_to_tutorialFragment)}

        binding.rankingBtn.setOnClickListener{findNavController().navigate(R.id.action_titleFragment_to_rankFragment)}

        return binding.root
    }

}
