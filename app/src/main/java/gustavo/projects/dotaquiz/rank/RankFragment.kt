package gustavo.projects.dotaquiz.rank

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders

import gustavo.projects.dotaquiz.R
import gustavo.projects.dotaquiz.databinding.RankFragmentBinding
import gustavo.projects.dotaquiz.model.RankDatabase

class RankFragment : Fragment() {

    private lateinit var viewModel: RankFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<RankFragmentBinding>(inflater, R.layout.rank_fragment, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = RankDatabase.getInstance(application).rankDatabaseDao

        val rankViewModelFactory = RankViewModelFactory(dataSource)

        val viewModel = ViewModelProviders.of(this, rankViewModelFactory).get(RankFragmentViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

}
