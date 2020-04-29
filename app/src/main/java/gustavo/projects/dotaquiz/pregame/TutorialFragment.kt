package gustavo.projects.dotaquiz.pregame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import gustavo.projects.dotaquiz.R
import gustavo.projects.dotaquiz.databinding.FragmentTutorialBinding


class TutorialFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentTutorialBinding>(inflater,
            R.layout.fragment_tutorial, container,false)

        binding.tutorialStartGameBtn.setOnClickListener{it:View -> findNavController().navigate(
            R.id.action_tutorialFragment_to_gameFragment
        )}

        return binding.root
    }

}
