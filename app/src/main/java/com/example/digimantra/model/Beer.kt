package com.example.digimantra.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Beer(
    val id: Int?,
    val name: String?,
    val tagline: String?,
    val description: String?,
    @SerializedName("image_url") val imageUrl: String?
) : Serializable