package com.rightside.hackaton.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Producer(val name : String, val email : String, val uuid : String) : Parcelable {
    constructor() : this ("", "", "")
}
