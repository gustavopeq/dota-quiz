package gustavo.projects.dotaquiz.rank

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import gustavo.projects.dotaquiz.R
import gustavo.projects.dotaquiz.databinding.RankFragmentBinding

class RankFragment : Fragment() {

    private lateinit var viewModel: RankFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<RankFragmentBinding>(inflater, R.layout.rank_fragment, container, false)

        

        return binding.root
    }

}
