package com.example.androidweek6

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidweek6.databinding.ItemFilmBinding
import com.example.androidweek6.fragment.DetailFragment
import com.example.androidweek6.model.Movie

class NowPlayAdapter(private val onClickListener: OnClickListener) : ListAdapter<Movie, NowPlayAdapter.NowPlayVH>(NowPlayDiffUtilCallback()) {
    class NowPlayDiffUtilCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }

    class NowPlayVH private constructor(var binding: ItemFilmBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): NowPlayVH {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemFilmBinding.inflate(layoutInflater, parent, false)
                return NowPlayVH(binding)
            }
        }
        fun binding(item: Movie) {
            binding.nameFilm.text = item.title?.trim()
            binding.describeFilm.text = item.overview?.trim()
            val urlImage = "http://image.tmdb.org/t/p/w500${item.posterPath}"
            Glide.with(itemView.context).load(urlImage).into(binding.imageFilm)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayVH {
        return NowPlayVH.from(parent)
    }

    override fun onBindViewHolder(holder: NowPlayVH, position: Int) {
        val movie = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(movie)
        }
        holder.binding(movie)
    }

    class OnClickListener(val clickListener : (movie : Movie) -> Unit){
        fun onClick(movie: Movie) = clickListener(movie)
    }
}