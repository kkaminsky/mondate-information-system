package com.kkaminsky.mondateinformationsystem.dto

data class FileDto(

        val fileName: String,

        val readFlag: Boolean,

        val writeFlag: Boolean,

        val deleteFlag: Boolean,

        val requestFlag: Boolean
)