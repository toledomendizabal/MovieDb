package com.test.moviedb.presentatiion

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.test.moviedb.R
import com.test.moviedb.core.domain.MovieDBItem
import com.test.moviedb.databinding.ItemBinding

class MainAdapter(private val listener: ItemListener) : RecyclerView.Adapter<MainAdapter.VH>() {
    private var items = mutableListOf<MovieDBItem>()


    inner class VH(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) = with(holder.binding) {
        Glide.with(moviePoster)
            .load("https://image.tmdb.org/t/p/original${items[position].poster_path}")
            .placeholder(R.drawable.broken)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(moviePoster)
        root.setOnClickListener {
            listener.onItemSelected(items[position].id, items[position].original_name == null)
        }
    }

    override fun getItemCount() = items.size


    fun updateItems(items: List<MovieDBItem>) {
        this.items += items
        notifyDataSetChanged()
    }

}