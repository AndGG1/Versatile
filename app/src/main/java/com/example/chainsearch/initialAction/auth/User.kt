package com.example.chainsearch.initialAction.auth

data class User(
    var username: String? = null,
    var lastTimeJoined: String? = null,
    var avgProductPrice: Double? = null,
    var clicks: Int? = null
)