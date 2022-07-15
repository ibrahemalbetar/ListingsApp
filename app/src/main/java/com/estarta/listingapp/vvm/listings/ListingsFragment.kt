package com.estarta.listingapp.vvm.listings

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.estarta.listingapp.data.models.ListingResp
import com.estarta.listingapp.vvm.base.BaseFragment
import com.estarta.listingapp.R
import com.estarta.listingapp.RequestStatus
import com.estarta.listingapp.databinding.FragmentListingBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


 interface OnItemClickListener {
    fun onItemClicked(position: Int, game: ListingResp.ListingItem?)
}

class ListingsFragment : BaseFragment<FragmentListingBinding, ListingsViewModel> (
    R.layout.fragment_listing
),OnItemClickListener {
    override val viewModel: ListingsViewModel by viewModel()

    private var listingsAdapter: ListingsAdapter? = null


    override fun ListingsViewModel.observeViewModel() {
        listingsList?.observe(viewLifecycleOwner, Observer {

           listingsAdapter!!.submitList(it)

        })

        status.observe(viewLifecycleOwner, Observer { state ->
            listingsAdapter?.setState(state ?: RequestStatus.LOADED)

        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        listingsAdapter = ListingsAdapter(activity, Glide.with(this), this)
        binding.recyclerView.adapter = listingsAdapter
        viewModel.refresh()
    }

    override fun onItemClicked(position: Int, game: ListingResp.ListingItem?) {
    }

}