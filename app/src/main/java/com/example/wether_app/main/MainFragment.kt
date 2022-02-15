package com.example.wether_app.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL
import com.example.wether_app.R
import com.example.wether_app.databinding.MainFragmentBinding
import com.example.wether_app.dialog.PermissionDialog
import com.example.wether_app.model.Weather

val NUM_PAGES = 3
class MainFragment : Fragment() {

    lateinit var permissionDialog: PermissionDialog

    val weathers = arrayOf(
        Weather("South Korea", "Seoul", -8, "CLOUD"),
        Weather("South Korea2", "Seoul", -8, "CLOUD"),
        Weather("South Korea3", "Seoul", -8, "CLOUD"),
        Weather("South Korea4", "Seoul", -8, "CLOUD"),
    )

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        binding.weatherPager.adapter = ScreenSlidePagerAdapter(this)
        binding.weatherPager.setPageTransformer(ViewPager2PageTransformation())

        binding.weatherList.layoutManager = LinearLayoutManager(this.context)
        binding.weatherList.addItemDecoration(WeatherCardListItemDecorator())

        binding.weatherList.adapter = WeatherCardListAdapter(weathers)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        permissionDialog = PermissionDialog()
        permissionDialog.show(childFragmentManager, "PERMISSION_DIALOG")
    }


    private inner class ScreenSlidePagerAdapter(fa: Fragment): FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = weathers.size
        override fun createFragment(position: Int): Fragment {
            val WeatherCardFragment = WeatherCardFragment.newInstance(position, weathers[position])

            return WeatherCardFragment
        }
    }

    private inner class ViewPager2PageTransformation: ViewPager2.PageTransformer {
        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin)
        val offsetPx = resources.getDimensionPixelOffset(R.dimen.offset)
        override fun transformPage(page: View, position: Float) {
            val viewPager = page.parent.parent as ViewPager2
            val offset = position * -(2 * offsetPx + pageMarginPx)
            if (viewPager.orientation == ORIENTATION_HORIZONTAL) {
                if (ViewCompat.getLayoutDirection(viewPager) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                    page.translationX = -offset
                } else {
                    page.translationX = offset
                }
            } else {
                page.translationY = offset
            }
        }
    }


}