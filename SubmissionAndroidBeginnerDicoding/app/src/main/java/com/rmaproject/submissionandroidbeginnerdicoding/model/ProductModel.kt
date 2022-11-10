package com.rmaproject.submissionandroidbeginnerdicoding.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductModel(
    var productName:String = "",
    var productPrice:String = "",
    var productDescription:String = "",
    var productRating:String = "",
    var productImageUrl:String = "",
    var productStoreUrl:String = ""
) : Parcelable
