package com.example.moviecatalogue

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.databinding.FragmentActivityBinding
import com.example.moviecatalogue.movie.MovieAdapter
import com.example.moviecatalogue.movie.MovieModel
import com.example.moviecatalogue.tvshow.TVShowModel
import com.example.moviecatalogue.tvshow.TvshowAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class Fragmentt : Fragment(){
    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"
        @JvmStatic
        fun newInstance(index: Int) =
            Fragmentt().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, index)
                }
            }
    }

    private lateinit var binding: FragmentActivityBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_activity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentActivityBinding.bind(view)
        binding.rvFrag.layoutManager = LinearLayoutManager(this.context)
        val index = arguments?.getInt(ARG_SECTION_NUMBER, 0)

        val mainViewModel: ViewModel by activityViewModels()

        when (index) {
            1 -> {

                val adapter = MovieAdapter()
                adapter.notifyDataSetChanged()
                binding.rvFrag.adapter = adapter
                binding.progressbar.visibility = View.VISIBLE

                mainViewModel.getMovie().observe(viewLifecycleOwner, { listMovies ->
                    if (listMovies != null) {
                        adapter.setMovies(listMovies)
                        binding.progressbar.visibility = View.GONE
                    }
                })

                adapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback {
                    override fun onItemClicked(data: MovieModel) {
                        val intent = Intent(activity, DetailActivity::class.java)
                        intent.putExtra(DetailActivity.EXTRA, data.id)
                        intent.putExtra(DetailActivity.EXTRA_SELECT, DetailActivity.EXTRA_MOVIES)
                        startActivity(intent)
                    }

                })
            }

            2 -> {

                val adapter = TvshowAdapter()
                adapter.notifyDataSetChanged()
                binding.rvFrag.adapter = adapter
                binding.progressbar.visibility = View.VISIBLE

                mainViewModel.getTv().observe(viewLifecycleOwner, { listTVs ->
                    if (listTVs != null) {
                        adapter.setTvshow(listTVs)
                        binding.progressbar.visibility = View.GONE
                    }
                })

                adapter.setOnItemClickCallback(object : TvshowAdapter.OnItemClickCallback {

                    override fun onItemClicked(data: TVShowModel) {
                        val intent = Intent(activity, DetailActivity::class.java)
                        intent.putExtra(DetailActivity.EXTRA, data.id)
                        intent.putExtra(DetailActivity.EXTRA_SELECT, DetailActivity.EXTRA_TV)
                        startActivity(intent)
                    }

                })
            }
        }
    }
}