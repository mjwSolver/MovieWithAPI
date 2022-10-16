package com.visualprogrammingclass.moviewithapi.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
import com.visualprogrammingclass.moviewithapi.R
import com.visualprogrammingclass.moviewithapi.databinding.CardProductionCompanyBinding
import com.visualprogrammingclass.moviewithapi.helper.Const
import com.visualprogrammingclass.moviewithapi.model.ProductionCompany
import kotlinx.coroutines.NonDisposableHandle.parent


class CompanyAdapter(private val dataSet: List<ProductionCompany>) :
        RecyclerView.Adapter<CompanyAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardBind = CardProductionCompanyBinding.bind(view)

        init {
            // Define click listener for the ViewHolder's View.
//            if(adapterPosition != -1) {
//            Glide.with(view.context)
//                .load(Const.IMG_URL + dataSet[adapterPosition].logo_path)
//                .into(cardBind.productionCompanyBrandImageView)
//          }
        }

        fun glideImage(view: View){
            Glide.with(view.context)
                .load(Const.IMG_URL + dataSet[adapterPosition].logo_path)
                .into(cardBind.productionCompanyBrandImageView)

//            cardBind.productionCompanyCardView.setCardBackgroundColor(d1fae5)
        }

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.card_production_company, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.glideImage(viewHolder.itemView)

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
