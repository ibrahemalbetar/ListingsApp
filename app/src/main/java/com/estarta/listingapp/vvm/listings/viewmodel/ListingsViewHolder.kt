package com.estarta.listingapp.vvm.listings.viewmodel

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.estarta.listingapp.data.models.ListingResp
import com.estarta.listingapp.databinding.ListingItemBinding
import com.estarta.listingapp.vvm.listings.OnItemClickListener

@SuppressLint("StaticFieldLeak")
var binding : ListingItemBinding? = null
class ListingsViewHolder(
    val context: FragmentActivity?,
    itemView: View,
    val glide: RequestManager,
    val listener: OnItemClickListener
) :
    RecyclerView.ViewHolder(itemView) {
    companion object {
        fun create(
            parent: ViewGroup,
            glide: RequestManager,
            context: FragmentActivity?,
            listener: OnItemClickListener
        ): ListingsViewHolder {
            binding = ListingItemBinding.inflate(LayoutInflater.from(parent.context))
            val view  = binding!!.root
            return ListingsViewHolder(context, view, glide, listener)
        }
    }

    fun bind(
        position: Int,
        listingItem: ListingResp.ListingItem?,
    ) {

        binding!!.titleTV.text= listingItem?.name ?: ""
        binding!!.root.setOnClickListener {
            listener.onItemClicked(position, listingItem)
        }
    }
}