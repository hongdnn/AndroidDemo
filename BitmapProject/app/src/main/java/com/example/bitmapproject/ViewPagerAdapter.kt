package com.example.bitmapproject

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(manager: FragmentManager, fragments: MutableList<Fragment>) : FragmentPagerAdapter(manager) {

    private var fragmentList: MutableList<Fragment> = fragments

    init {
        fragmentList = fragments
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }
}