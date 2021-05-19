package com.example.moviecatalogue

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.example.moviecatalogue.R
import com.example.moviecatalogue.SectionPagerAdapter
import com.example.moviecatalogue.databinding.ActivityMainBinding
import com.example.moviecatalogue.favoritee.FavoriteActivity
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object {
        @StringRes
        val TAB_TITLES = intArrayOf(
             R.string.movie,
            R.string.tvshow
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportActionBar?.elevation = 0f

        val sectionsPagerAdapter = SectionPagerAdapter(this@MainActivity)
        binding.viewPager.adapter = sectionsPagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorite -> startActivity(Intent(this@MainActivity, FavoriteActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

}