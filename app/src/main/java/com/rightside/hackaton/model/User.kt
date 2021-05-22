package com.rightside.hackaton.model

data class User(val name: String, val email: String, val pictureUrl: String, val uuid : String) {
    constructor() : this ("", "", "", "")
}