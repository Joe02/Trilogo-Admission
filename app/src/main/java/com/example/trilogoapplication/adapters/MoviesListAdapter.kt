package com.example.trilogoapplication.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.trilogoapplication.R
import com.example.trilogoapplication.databinding.ItemMovieListBinding
import com.example.trilogoapplication.models.Movie


class MoviesListAdapter(private val moviesList: List<Movie>, private val context: Context) :
    RecyclerView.Adapter<MoviesListAdapter.ItemViewHolder>() {

    var onItemClick: ((Movie) -> Unit)? = null
    private var inflater: LayoutInflater? = null
    private var parent: ViewGroup? = null
    private val bindings: MutableList<ItemMovieListBinding> = mutableListOf()
    private var cardsHeight: MutableList<ConstraintLayout> = mutableListOf()
    private var cardsArrows: MutableList<ImageView> = mutableListOf()
    private val FADE_DURATION = 1000 //FADE_DURATION in milliseconds


    inner class ItemViewHolder(binding: ItemMovieListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val movieTitle: TextView = binding.movieTitleTextView
        val movieEnglishTitle: TextView = binding.englishTitleTextView
        val cardLayout: ConstraintLayout = binding.cardConstraintLayout
        val movieSinopsis: TextView = binding.movieSinopsis
        val detailsArrow: ImageView = binding.openDetailsArrow
        val moviePoster: ImageView = binding.moviePoster

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(moviesList[adapterPosition])
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesListAdapter.ItemViewHolder {
        this.parent = parent
        val inflater = this.inflater ?: LayoutInflater.from(parent.context)

        if (this.inflater == null) {
            this.inflater = inflater
        }

        val homeScreenBinding = DataBindingUtil.inflate<ItemMovieListBinding>(
            inflater,
            R.layout.item_movie_list,
            parent,
            false
        )
        bindings.add(homeScreenBinding)

        return ItemViewHolder(homeScreenBinding)
    }

    override fun getItemCount(): Int = moviesList.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MoviesListAdapter.ItemViewHolder, position: Int) {
        cardsHeight.add(position, holder.cardLayout)
        cardsArrows.add(position, holder.detailsArrow)

        holder.movieTitle.text = moviesList[position].originalTitle
        if (moviesList[position].movieLanguage != "en") {
            holder.movieEnglishTitle.text = "(${moviesList[position].title})"
        }
        holder.movieEnglishTitle.typeface = Typeface.create(
            Typeface.DEFAULT, Typeface.ITALIC
        )

        if (moviesList[position].overview?.length!! > 70) {
            holder.movieSinopsis.text = moviesList[position].overview?.substring(0, 70) + "..."
        } else {
            holder.movieSinopsis.text = moviesList[position].overview
        }

        Glide.with(context)
            .load("https://image.tmdb.org/t/p/w500/" + moviesList[position].posterPath)
            .into(holder.moviePoster)

        cardsHeight[position].setOnClickListener {
            if (cardsHeight[position].height < 210) {
                holder.movieSinopsis.text = moviesList[position].overview
                cardsArrows[position].setImageResource(R.drawable.arrow_up)
                cardsHeight[position].layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    120 + (moviesList[position].overview?.length?.times(
                        1
                    )!!).toInt() - 10
                )

            } else {
                holder.movieSinopsis.text = moviesList[position].overview?.substring(0, 70) + "..."
                cardsArrows[position].setImageResource(R.drawable.arrow_down)
                cardsHeight[position].layoutParams =
                    LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 150)
            }
        }

        holder.setIsRecyclable(false)
        setFadeAnimation(holder.itemView)
    }

    private fun setFadeAnimation(view: View) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = FADE_DURATION.toLong()
        view.startAnimation(anim)
    }

}