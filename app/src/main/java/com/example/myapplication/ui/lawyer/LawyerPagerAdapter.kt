package com.example.myapplication.ui.lawyer

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class LawyerPagerAdapter(fm:FragmentManager):FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return LawyerTab1Fragment()
            }

            else -> return LawyerTab2Fragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
         when(position){
            0 -> return "服务方式"
             else -> return "用户评价"
        }
    }
}