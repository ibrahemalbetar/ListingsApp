package com.estarta.listingapp.vvm.listings

import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.estarta.listingapp.RequestStatus
import com.estarta.listingapp.data.models.ListingResp
import com.estarta.listingapp.vvm.listings.viewmodel.ListingsViewHolder

class ListingsAdapter(
    val context: FragmentActivity?,
    val glide: RequestManager,
    val listener: OnItemClickListener
) :
    PagedListAdapter<ListingResp.ListingItem, RecyclerView.ViewHolder>(LISTING_COMPARATOR) {

    companion object {

        val LISTING_COMPARATOR = object : DiffUtil.ItemCallback<ListingResp.ListingItem>() {
            override fun areContentsTheSame(
                oldItem: ListingResp.ListingItem,
                newItem: ListingResp.ListingItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(
                oldItem: ListingResp.ListingItem,
                newItem: ListingResp.ListingItem
            ): Boolean {
                return oldItem.uid == newItem.uid
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ListingsViewHolder.create(parent, glide, context, listener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                val item = getItem(position) ?: return
                (holder as ListingsViewHolder).bind(position, item)
    }

    private var state = RequestStatus.LOADED

    fun setState(state: RequestStatus) {
        this.state = state
        notifyDataSetChanged()
    }

}
