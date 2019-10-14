package com.kkaminsky.mondateinformationsystem.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class GetAllUsersDto(

        @JsonProperty("userCheck")
        val userCheck: UserCheckDto

)