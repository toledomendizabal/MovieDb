package com.test.moviedb.presentatiion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.moviedb.R
import com.test.moviedb.core.domain.Response
import com.test.moviedb.databinding.ActivityMainBinding
import com.test.moviedb.network.NetworkService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), ItemListener {

    private val viewModel by viewModel<MainViewModel>()

    private lateinit var binding: ActivityMainBinding

    private lateinit var popularMovieAdapter: MainAdapter
    private lateinit var playingNowMovieAdapter: MainAdapter
    private lateinit var popularTvShowsAdapter: MainAdapter
    private lateinit var topRatedTvShowsAdapter: MainAdapter
    private lateinit var popularMoviesLayoutManager: LinearLayoutManager
    private lateinit var playingNowMoviesLayoutManager: LinearLayoutManager
    private lateinit var popularTvShowsLayoutManager: LinearLayoutManager
    private lateinit var topRatedTvShowsLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        popularMoviesLayoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        playingNowMoviesLayoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        popularTvShowsLayoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        topRatedTvShowsLayoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

        popularMovieAdapter = MainAdapter(this)
        playingNowMovieAdapter = MainAdapter(this)
        popularTvShowsAdapter = MainAdapter(this)
        topRatedTvShowsAdapter = MainAdapter(this)

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            content.playingNowRecyclerView.layoutManager = playingNowMoviesLayoutManager
            content.playingNowRecyclerView.adapter = playingNowMovieAdapter
            content.popularMoviesRecyclerView.layoutManager = popularMoviesLayoutManager
            content.popularMoviesRecyclerView.adapter = popularMovieAdapter
            content.popularTvShowsRecyclerView.layoutManager = popularTvShowsLayoutManager
            content.popularTvShowsRecyclerView.adapter = popularTvShowsAdapter
            content.topRatedTvShowsRecyclerView.layoutManager = topRatedTvShowsLayoutManager
            content.topRatedTvShowsRecyclerView.adapter = topRatedTvShowsAdapter

            content.playingNowRecyclerView.addOnScrollListener(object :
                RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollHorizontally(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        viewModel.loadPlayingNowMovies()
                    }
                }
            })

            content.popularMoviesRecyclerView.addOnScrollListener(object :
                RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollHorizontally(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        viewModel.loadPopularMovies()
                    }
                }
            })

            content.popularTvShowsRecyclerView.addOnScrollListener(object :
                RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollHorizontally(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        viewModel.loadPopularSeries()
                    }
                }
            })

            content.topRatedTvShowsRecyclerView.addOnScrollListener(object :
                RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollHorizontally(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        viewModel.loadTopRatedSeries()
                    }
                }
            })

            setContentView(root)
        }

        viewModel.loadData()


        viewModel.playingNowMovies.observe(this@MainActivity) {
            when (it) {
                is Response.Loading -> {
                    binding.content.playingNowLoader.visibility = View.VISIBLE
                }
                is Response.Success -> {
                    binding.content.playingNowLoader.visibility = View.GONE
                    playingNowMovieAdapter.updateItems(it.data)
                }
                else -> {
                    binding.content.playingNowLoader.visibility = View.GONE
                }
            }
        }

        viewModel.popularMovies.observe(this@MainActivity) {
            when (it) {
                is Response.Loading -> {
                    binding.content.popularMoviesLoader.visibility = View.VISIBLE
                }
                is Response.Success -> {
                    binding.content.popularMoviesLoader.visibility = View.GONE
                    popularMovieAdapter.updateItems(it.data)
                }
                else -> {
                    binding.content.popularMoviesLoader.visibility = View.GONE
                }
            }
        }


        viewModel.popularSeries.observe(this@MainActivity) {
            when (it) {
                is Response.Loading -> {
                    binding.content.popularTvShowsLoader.visibility = View.VISIBLE
                }
                is Response.Success -> {
                    binding.content.popularTvShowsLoader.visibility = View.GONE
                    popularTvShowsAdapter.updateItems(it.data)
                }
                else -> {
                    binding.content.popularTvShowsLoader.visibility = View.GONE
                }
            }
        }


        viewModel.topRatedMovies.observe(this@MainActivity) {
            when (it) {
                is Response.Loading -> {
                    binding.content.topRatedTvShowsLoader.visibility = View.VISIBLE
                }
                is Response.Success -> {
                    binding.content.topRatedTvShowsLoader.visibility = View.GONE
                    topRatedTvShowsAdapter.updateItems(it.data)
                }
                else -> {
                    binding.content.topRatedTvShowsLoader.visibility = View.GONE
                }
            }
        }
    }

    override fun onItemSelected(id: Int, isMovie: Boolean) {
        supportFragmentManager.beginTransaction()
            .replace(binding.container.id, DetailFragment.newInstance(id, isMovie))
            .addToBackStack("Movie Detail").commit()
    }
}