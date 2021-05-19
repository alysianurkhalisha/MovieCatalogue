package com.example.moviecatalogue.favoritee

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecatalogue.DetailActivity
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.FragmentFavoriteBinding
import com.example.moviecatalogue.movie.MovieAdapter
import com.example.moviecatalogue.movie.MovieModel
import com.example.moviecatalogue.movie.MoviePaging
import com.example.moviecatalogue.tvshow.TVShowModel
import com.example.moviecatalogue.tvshow.TvshowAdapter
import com.example.moviecatalogue.tvshow.TvshowPaging
import com.example.moviecatalogue.utils.Sort
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class FavoFragment : Fragment() {
    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"
        @JvmStatic
        fun newInstance(index: Int) =
            FavoFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, index)
                }
            }
    }

    private lateinit var binding: FragmentFavoriteBinding
    private val favoriteViewModel: ViewModelFavo by activityViewModels()
    private val movieAdapter = MoviePaging()
    private val tvShowAdapter = TvshowPaging()

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentFavoriteBinding.bind(view)
        binding.rvFrag.layoutManager = LinearLayoutManager(this.context)
        val index = arguments?.getInt(ARG_SECTION_NUMBER, 0)


        when (index) {
            1 -> {
                movieAdapter.notifyDataSetChanged()
                binding.rvFrag.adapter = movieAdapter
                binding.progressbar.visibility = View.VISIBLE

                observeData(Sort.TYPE_MOVIE,Sort.TITLE)

                movieAdapter.setOnItemClickCallback(object : MoviePaging.OnItemClickCallback {
                    override fun onItemClicked(data: MovieModel) {
                        val intent = Intent(activity, DetailActivity::class.java)
                        intent.putExtra(DetailActivity.EXTRA, data.id)
                        intent.putExtra(DetailActivity.EXTRA_SELECT, DetailActivity.EXTRA_MOVIES)
                        startActivity(intent)
                    }

                })
            }

            2 -> {
                tvShowAdapter.notifyDataSetChanged()
                binding.rvFrag.adapter = tvShowAdapter
                binding.progressbar.visibility = View.VISIBLE

                observeData(Sort.TYPE_TVSHOW, Sort.TITLE)

                tvShowAdapter.setOnItemClickCallback(object : TvshowPaging.OnItemClickCallback {

                    override fun onItemClicked(data: TVShowModel) {
                        val intent = Intent(activity, DetailActivity::class.java)
                        intent.putExtra(DetailActivity.EXTRA, data.id)
                        intent.putExtra(DetailActivity.EXTRA_SELECT, DetailActivity.EXTRA_TV)
                        startActivity(intent)
                    }

                })
            }
        }
        ItemTouchHelper (object : ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                when (index) {
                    1 -> {
                        val movieList = movieAdapter.currentList?.get(viewHolder.adapterPosition)
                        if (movieList != null) {
                            favoriteViewModel.deleteMovie(movieList)
                        }
                    }
                    2 -> {
                        val tvList = tvShowAdapter.currentList?.get(viewHolder.adapterPosition)
                        if (tvList != null) {
                            favoriteViewModel.deleteTVShow(tvList)
                        }
                    }
                }
            }
        }).attachToRecyclerView(binding.rvFrag)
    }

override fun onOptionsItemSelected(item: MenuItem): Boolean {
    var sort = ""
    when (item.itemId) {
        R.id.titleaction -> sort = Sort.TITLE
        R.id.ratingaction -> sort = Sort.TOP_RATING

    }
    observeData(Sort.TYPE_MOVIE, sort)
    observeData(Sort.TYPE_TVSHOW, sort)
    item.isChecked = true
    return super.onOptionsItemSelected(item)
}

private fun observeData(type: String, sort: String){
    when (type) {
        Sort.TYPE_MOVIE -> {
            favoriteViewModel.getAllMovies(sort).observe(viewLifecycleOwner, { listMovies ->
                if (listMovies != null) {
                    movieAdapter.submitList(listMovies)
                    binding.progressbar.visibility = View.GONE
                }
            })
        }
        Sort.TYPE_TVSHOW -> {
            favoriteViewModel.getAllTVShows(sort).observe(viewLifecycleOwner, { listTVs ->
                if (listTVs != null) {
                    tvShowAdapter.submitList(listTVs)
                    binding.progressbar.visibility = View.GONE
                }
            })
        }
    }
}
}