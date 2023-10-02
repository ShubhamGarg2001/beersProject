package com.example.digimantra.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.example.digimantra.R
import com.example.digimantra.databinding.FragmentBeerDetailsBinding

/*
Transition animations from beer list screen to beer detail screen are provided in app_nav_graph.xml
 */
class BeerDetailsFragment : Fragment() {
    private var mBinding: FragmentBeerDetailsBinding? = null
    private val args: BeerDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_beer_details, container, false)
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Used data binding to bind the beer object with xml.
        mBinding?.beer = args.beer
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }


}