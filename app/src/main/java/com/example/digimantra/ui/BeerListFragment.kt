package com.example.digimantra.ui

import android.os.Bundle
import androidx.lifecycle.Lifecycle.State
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.digimantra.R
import com.example.digimantra.databinding.FragmentBeerListBinding
import com.example.digimantra.model.Beer
import com.example.digimantra.ui.adapters.BeersAdapter
import com.example.digimantra.ui.adapters.LoaderAdapter
import com.example.digimantra.viewmodel.BeerListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BeerListFragment : Fragment() {
    private var mBinding: FragmentBeerListBinding? = null
    private var beersAdapter: BeersAdapter? = null
    private val mViewModel by activityViewModels<BeerListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_beer_list, container, false)
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectFlows()
        setUpRecyclerView()
        attachClickListeners()
    }

    private fun attachClickListeners() {
        mBinding?.retryNow?.setOnClickListener {
            beersAdapter?.retry()
        }
    }

    /*
    Function to collect data as flow from viewModel as submit to the adapter. Loading and error states are handled using loadStateflow
    provided by PagingDataAdapter.
    Here paging is handled using Paging 3 native library by android.
     */
    private fun collectFlows() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(State.STARTED) {
                launch {
                    mViewModel.beersListFlow.collectLatest {
                        beersAdapter?.submitData(it)
                    }
                }
                launch {
                    beersAdapter?.loadStateFlow?.collectLatest { loadStates ->
                        mBinding?.loader?.isVisible = loadStates.refresh is LoadState.Loading
                        mBinding?.retryNow?.isVisible = loadStates.refresh is LoadState.Error
                    }
                }
            }
        }
    }

    /*
    Here adapter is set to recyclerview and navigation to detail screen is managed through android NavGraph.
     */
    private fun setUpRecyclerView() {
        beersAdapter = BeersAdapter(::navigateToBeerDetailScreen)
        mBinding?.beersRecyclerView?.adapter =
            beersAdapter?.withLoadStateFooter(footer = LoaderAdapter {
                beersAdapter?.retry()
            })
    }

    private fun navigateToBeerDetailScreen(beer: Beer?) {
        beer?.let {
            val action =
                BeerListFragmentDirections.actionBeerListFragmentToBeerDetailsFragment(it)
            findNavController().navigate(action)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }
}