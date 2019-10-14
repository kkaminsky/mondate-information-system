package com.kkaminsky.mondateinformationsystem.service

import com.kkaminsky.mondateinformationsystem.dto.FileDto
import com.kkaminsky.mondateinformationsystem.dto.RequestDto
import com.kkaminsky.mondateinformationsystem.dto.UserCheckDto
import com.kkaminsky.mondateinformationsystem.dto.UserDto

interface UserService {

    fun login(username: String, password:String): UserCheckDto
    fun register(username: String, password: String)
    fun registerAdmin(username: String, password: String)
    fun getFilesForUser(username: String): List<FileDto>
    fun readFile(username: String,fileName:String): String
    fun writeFile(username: String,fileName: String,text:String)
    fun sendDeleteRequest(username: String,fileName: String)
    fun deleteFile(fileName: String)
    fun getRequests(): List<RequestDto>
    fun editFile(fileName: String, level: Int)
    fun edit(oldUsername: String, newUsername: String, newRole: String, newLevel:Int)
    fun getAllUsers(): List<UserDto>
    fun createFile(fileName: String, username: String)

}