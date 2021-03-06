package com.hao.library.ui

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.hao.library.R
import com.hao.library.adapter.FragmentAdapter
import com.hao.library.annotation.Base

/**
 * @author Yang Shihao
 */
@Base
abstract class BaseViewPagerFragment<VB : ViewBinding, VM : ViewModel> : BaseFragment<VB, VM>() {

    @CallSuper
    override fun initView() {
        val viewPager: ViewPager2 = f(R.id.baseViewPager)!!
        val tabLayout: TabLayout = f(R.id.baseTabLayout)!!
        val titles = getTitles()
        val fragments = getFragments()
        viewPager.adapter = FragmentAdapter(childFragmentManager, lifecycle, fragments)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            if (position in titles.indices) {
                tab.text = titles[position]
            }
        }.attach()
    }

    override fun initData() {

    }

    abstract fun getTitles(): List<String>

    abstract fun getFragments(): List<FragmentCreator>
}