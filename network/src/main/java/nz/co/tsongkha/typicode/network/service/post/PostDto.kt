package nz.co.tsongkha.typicode.network.service.post

import com.google.gson.annotations.SerializedName

data class PostDto(

    @SerializedName("userId") val userId: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String
)