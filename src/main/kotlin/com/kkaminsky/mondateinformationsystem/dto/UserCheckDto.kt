package com.kkaminsky.mondateinformationsystem.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class UserCheckDto(
        @JsonProperty("username")
        val username: String,

        @JsonProperty("userRole")
        val userRole: String,

        @JsonProperty("signature")
        val signature: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserCheckDto

        if (username != other.username) return false
        if (userRole != other.userRole) return false
        if (!signature.contentEquals(other.signature)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = username.hashCode()
        result = 31 * result + userRole.hashCode()
        result = 31 * result + signature.contentHashCode()
        return result
    }
}