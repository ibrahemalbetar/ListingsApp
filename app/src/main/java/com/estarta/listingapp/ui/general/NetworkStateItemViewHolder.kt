
package com.estarta.listingapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.estarta.listingapp.R

class NetworkStateItemViewHolder(view: View)
    : RecyclerView.ViewHolder(view) {

    companion object {
        fun create(parent: ViewGroup): NetworkStateItemViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.listing_placeholder, parent, false)
            return NetworkStateItemViewHolder(
                view
            )
        }
    }

    fun bind() {

    }
}
