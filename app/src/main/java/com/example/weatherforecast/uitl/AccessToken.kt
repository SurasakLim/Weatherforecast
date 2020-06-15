package com.example.weatherforecast.uitl

import com.google.gson.annotations.SerializedName

open class AccessToken (
    @SerializedName("userId")
    var userId: String,

    @SerializedName("access_token")
    var accessToken: String?,

    @SerializedName("refresh_token")
    var refreshToken: String?,

    @SerializedName("token_type")
    var tokenType: String?,

    @SerializedName("expires_in")
    var expiresIn: Long
){
    constructor() : this("", "", "", "", 0)
}
