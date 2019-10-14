package com.kkaminsky.mondateinformationsystem.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class EditDto(

        @JsonProperty("userCheck")
        val userCheck: UserCheckDto,

        @JsonProperty("oldUsername")
        val oldUsername: String,

        @JsonProperty("newUsername")
        val newUsername: String,

        @JsonProperty("newLevel")
        val newLevel: Int,

        @JsonProperty("newRole")
        val newRole: String
)