package com.estarta.listingapp.vvm.listings

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
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

        glide.load(listingItem!!.imageUrls?.get(0))
            .centerCrop()
            .thumbnail(glide.load(listingItem.imageUrlsThumbnails!![0]).centerCrop().thumbnail(0.4f))
            .transition(DrawableTransitionOptions.withCrossFade())
            .into( binding!!.ImageView)

        binding!!.titleTV.text= listingItem?.name ?: ""

        binding!!.root.setOnClickListener {
            listener.onItemClicked(position, listingItem)
        }
    }
}