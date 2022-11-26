package com.example.myapplication.ui.activity.China

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.myapplication.ui.lawyer.LawyerTab1Fragment
import com.example.myapplication.ui.lawyer.LawyerTab2Fragment

class VonPagerAdapter(fm:FragmentManager):FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return VendorFragment()
            }

            else -> return VendorFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> return "通讯"
            else -> return "硬件"
        }
    }

}