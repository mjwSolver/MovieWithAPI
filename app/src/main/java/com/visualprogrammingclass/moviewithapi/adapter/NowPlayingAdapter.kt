package com.visualprogrammingclass.moviewithapi.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.visualprogrammingclass.moviewithapi.R
import com.visualprogrammingclass.moviewithapi.databinding.CardNowPlayingBinding
import com.visualprogrammingclass.moviewithapi.model.NowPlaying
import com.visualprogrammingclass.moviewithapi.model.NowPlayingResult
import com.visualprogrammingclass.moviewithapi.model.UpcomingResult
import com.visualprogrammingclass.moviewithapi.view.MovieDetailActivity

class NowPlayingAdapter(private val dataSet: ArrayList<NowPlayingResult>) :
    RecyclerView.Adapter<NowPlayingAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (upcoming ViewHolder).
     */
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView
        val tvReleased: TextView
        val cvNowPlaying: CardView

        val movieCard: CardNowPlayingBinding = CardNowPlayingBinding.bind(view)

        init {
            // Define click listener for the ViewHolder's View.
            tvTitle = movieCard.movieTitleTextView
            tvReleased = movieCard.releaseDataTextView
            cvNowPlaying = movieCard.cvNowPlaying

        }

        fun onClickToDetail(theContext: Context) {
            val intenting = Intent(theContext, MovieDetailActivity::class.java)
                .putExtra("movieid", dataSet[adapterPosition].id)
            theContext.startActivity(intenting)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_now_playing, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.tvTitle.text = dataSet[position].title
        viewHolder.tvReleased.text = dataSet[position].release_date
        viewHolder.cvNowPlaying.setOnClickListener{
            viewHolder.onClickToDetail(it.context)
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
