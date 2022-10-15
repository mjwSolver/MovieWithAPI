package com.visualprogrammingclass.moviewithapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.visualprogrammingclass.moviewithapi.R
import com.visualprogrammingclass.moviewithapi.databinding.CardGenreBinding
import com.visualprogrammingclass.moviewithapi.model.Genre


class GenreAdapter(private val dataSet: List<Genre>) :
        RecyclerView.Adapter<GenreAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardBinding = CardGenreBinding.bind(view)

        init {
            // Define click listener for the ViewHolder's View.
//            if(adapterPosition != -1) {
//                cardBinding.genreTextViewInCardView.text = dataSet[adapterPosition].name
//            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.card_genre, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.cardBinding.genreTextViewInCardView.text = dataSet[position].name
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
