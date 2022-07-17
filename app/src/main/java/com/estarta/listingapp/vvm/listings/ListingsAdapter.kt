package com.estarta.listingapp.vvm.listings

import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.estarta.listingapp.RequestStatus
import com.estarta.listingapp.data.models.ListingResp
import com.estarta.listingapp.ui.NetworkStateItemViewHolder

class ListingsAdapter(
    val context: FragmentActivity?,
    val glide: RequestManager,
    val listener: OnItemClickListener
) :
    PagedListAdapter<ListingResp.ListingItem, RecyclerView.ViewHolder>(LISTING_COMPARATOR) {

    private val DATA_VIEW_TYPE = 1
    private val LOADING = 2

    companion object {

        val LISTING_COMPARATOR = object : DiffUtil.ItemCallback<ListingResp.ListingItem>() {
            override fun areContentsTheSame(
                oldItem: ListingResp.ListingItem,
                newItem: ListingResp.ListingItem
            ): Boolean {
                return true
            }

            override fun areItemsTheSame(
                oldItem: ListingResp.ListingItem,
                newItem: ListingResp.ListingItem
            ): Boolean {
                return true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            DATA_VIEW_TYPE -> {
                ListingsViewHolder.create(parent, glide, context, listener)
            }
            else -> {
                NetworkStateItemViewHolder.create(parent)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            DATA_VIEW_TYPE -> {
                val item = getItem(position) ?: return
                (holder as ListingsViewHolder).bind(position, item)
            }
            else -> {
                (holder as NetworkStateItemViewHolder).bind()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (isLoadingView()) {
            LOADING
        } else {
            DATA_VIEW_TYPE
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (isLoadingView()) 9 else 0
    }


    private var state = RequestStatus.LOADING

    fun setState(state: RequestStatus) {
        this.state = state
        notifyDataSetChanged()
    }

    private fun isLoadingView(): Boolean {
        return  state != null && (state == RequestStatus.LOADING || state == RequestStatus.FAILED)
    }


}
