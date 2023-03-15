package com.example.redmuriapp.domain.entities

data class User(
    var id: Int,
    var firstName: String,
    var lastName: String,
    var email: String,
    var password: String,
    var location: String,
    var profileImage: String?,
)
