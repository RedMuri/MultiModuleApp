package com.example.data.model

data class User(
    var firstName: String,
    var lastName: String,
    var email: String,
    var password: String= "123456",
    var location: String="Moscow",
    var profileImage: String? = null,
    var id: Int = UNDEFINED_ID
)
{
    companion object{
        const val UNDEFINED_ID = 0
    }
}