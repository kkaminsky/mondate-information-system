package com.kkaminsky.mondateinformationsystem.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class GetFilesDto(

        @JsonProperty("userCheck")
        val userCheck: UserCheckDto
)