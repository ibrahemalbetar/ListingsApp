package com.estarta.listingapp.data.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class ListingResp(
    @SerializedName("results")
    val results: List<ListingItem>? = null
) : Parcelable {
    data class ListingItem(
        @SerializedName("created_at")
        val createdAt: String? = null,
        @SerializedName("image_ids")
        val imageIds: List<String>? = null,
        @SerializedName("image_urls")
        val imageUrls: List<String>? = null,
        @SerializedName("image_urls_thumbnails")
        val imageUrlsThumbnails: List<String>? = null,
        @SerializedName("name")
        val name: String? = null,
        @SerializedName("price")
        val price: String? = null,
        @SerializedName("uid")
        val uid: String? = null
    ): Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.createStringArrayList(),
            parcel.createStringArrayList(),
            parcel.createStringArrayList(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(createdAt)
            parcel.writeStringList(imageIds)
            parcel.writeStringList(imageUrls)
            parcel.writeStringList(imageUrlsThumbnails)
            parcel.writeString(name)
            parcel.writeString(price)
            parcel.writeString(uid)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<ListingItem> {
            override fun createFromParcel(parcel: Parcel): ListingItem {
                return ListingItem(parcel)
            }

            override fun newArray(size: Int): Array<ListingItem?> {
                return arrayOfNulls(size)
            }
        }
    }
}