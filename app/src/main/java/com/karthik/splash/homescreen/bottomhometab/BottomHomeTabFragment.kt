package com.karthik.splash.homescreen.bottomhometab


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.karthik.splash.R
import com.karthik.splash.homescreen.HomeScreen
import com.karthik.splash.homescreen.bottomtab.BottomTabTypes
import com.karthik.splash.misc.Utils
import kotlinx.android.synthetic.main.fragment_bottom_tab_home.*

class BottomHomeTabFragment : Fragment() {

    companion object {
        fun getInstance(iscached: Boolean): BottomHomeTabFragment {
            val args = Bundle()
            args.putBoolean(HomeScreen.IS_FROM_CACHE, iscached)
            val fragment = BottomHomeTabFragment()
            fragment.arguments = args
            fragment.retainInstance = true
            return fragment
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? =
            inflater.inflate(R.layout.fragment_bottom_tab_home, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        intialiseTab()
    }

    private fun intialiseTab() {
        val titles = arrayListOf(
                BottomTabTypes.New(requireContext().getString(R.string.frag_title_1)),
                BottomTabTypes.Trending(requireContext().getString(R.string.frag_title_2)),
                BottomTabTypes.Featured(requireContext().getString(R.string.frag_title_3))
        )

        viewpager.adapter = BottomHomeTabViewPagerAdapter(
                fragmentManager = childFragmentManager,
                pagetitles = titles,
                isfromcache = arguments?.getBoolean(HomeScreen.IS_FROM_CACHE) ?: false)

        viewpager.offscreenPageLimit = Utils.offScreenPageLimit
        tabs.setupWithViewPager(viewpager)
    }
}