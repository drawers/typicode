package nz.co.tsongkha.typicode.network.service.post

import com.google.gson.annotations.SerializedName

data class CommentDto(
    @SerializedName("postId") val postId: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("body") val body: String
)