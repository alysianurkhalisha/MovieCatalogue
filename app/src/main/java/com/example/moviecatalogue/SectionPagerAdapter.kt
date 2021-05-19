package com.example.moviecatalogue

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        return Fragmentt.newInstance(position + 1)
    }

    override fun getItemCount(): Int {
        return 2
    }


}