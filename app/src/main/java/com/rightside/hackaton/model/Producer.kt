package com.rightside.hackaton.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Producer(val name : String, val email : String, val id : String, val profilePicture : String, val photos : List<String>, val address : String, val companyName : String) : Parcelable {
    constructor() : this ("", "", "", "", listOf(), "", "")
}
