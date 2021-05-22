package com.rightside.hackaton.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Action(val description : String, val id : String, val name : String, val purchasePrice : Double, val quantity : Long, val salePrice : Double) : Parcelable {
    constructor() : this ("", "", "", 0.0, 0, 0.0)
}