package com.example.moviecatalogue.favoritee

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.example.moviecatalogue.databinding.ActivityFavoriteBinding
import com.example.moviecatalogue.utils.Const
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import com.example.moviecatalogue.favoritee.FavoPagerAdapter as FavoPagerAdapter


@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavoriteBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportActionBar?.elevation = 0f

        val sectionsPagerAdapter = FavoPagerAdapter(this@FavoriteActivity)
        binding.viewPager.adapter = sectionsPagerAdapter

        TabLayoutMediator(binding.tab, binding.viewPager) { tab, position ->
            tab.text = resources.getString(Const.TAB_TITLES[position])
        }.attach()

    }
}

