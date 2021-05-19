package com.example.moviecatalogue.favoritee

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class FavoPagerAdapter (activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        return FavoFragment.newInstance(position + 1)
    }

    override fun getItemCount(): Int {
        return 2
    }

}