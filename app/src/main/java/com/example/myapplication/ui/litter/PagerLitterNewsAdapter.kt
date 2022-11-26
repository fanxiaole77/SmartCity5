package com.example.myapplication.ui.litter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.myapplication.network.LitterNewsType

class PagerLitterNewsAdapter(fm:FragmentManager,val list: List<LitterNewsType.Row>):FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return LitterNewsListFragment.newInstance(list[position].id.toString(),"")
    }

    override fun getCount(): Int {
       return list.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return list[position].name
    }


}