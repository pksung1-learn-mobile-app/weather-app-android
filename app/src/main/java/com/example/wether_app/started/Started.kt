package com.example.wether_app.started

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.wether_app.R
import com.example.wether_app.databinding.StartedFragmentBinding

private const val NUM_PAGES = 5

class Started : Fragment() {


    companion object {
        fun newInstance() = Started()
    }

    private lateinit var viewModel: StartedViewModel
    private lateinit var binding: StartedFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.started_fragment,container, false )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(StartedViewModel::class.java)
        binding.cardViewPager.adapter = ScreenSlidePagerAdapter(this)
        // TODO: Use the ViewModel
    }

    private inner class ScreenSlidePagerAdapter(fa: Fragment): FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = NUM_PAGES
        override fun createFragment(position: Int): Fragment {
            val bottomCardFragment = BottomCardFragment.newInstance(position)
            return bottomCardFragment
        }
    }

}