package com.kkaminsky.mondateinformationsystem.controller

import com.kkaminsky.mondateinformationsystem.dto.*
import com.kkaminsky.mondateinformationsystem.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/user")
class UserController @Autowired constructor(
        val userService: UserService
) {

    @PostMapping("/all")
    fun getAllUsers(@RequestBody dto: GetAllUsersDto): List<UserDto> {

        return userService.getAllUsers()
    }

    @PostMapping("/login")
    fun login(@RequestBody dto: LoginDto): UserCheckDto {
        return userService.login(dto.username, dto.password)
    }

    @PostMapping("/register")
    fun register(@RequestBody dto: RegisterDto) {
        userService.register(dto.username, dto.password)
    }

    @PostMapping("/register/admin")
    fun registerAdmin(@RequestBody dto: RegisterDto) {
        userService.registerAdmin(dto.username, dto.password)
    }

    @PostMapping("/edit")
    fun edit(@RequestBody dto: EditDto){
        userService.edit(dto.oldUsername, dto.newUsername, dto.newRole,dto.newLevel)
    }

    @PostMapping("/get/files")
    fun getFilesForUser(@RequestBody dto:GetFilesDto):List<FileDto>{
        return userService.getFilesForUser(dto.userCheck.username)
    }


    @PostMapping("/get/reqs")
    fun getReqs(@RequestBody dto:GetFilesDto): List<RequestDto>{
        return userService.getRequests()
    }

    @PostMapping("/create/file")
    fun createFile(@RequestBody dto: CreateFileDto){
        userService.createFile(dto.fileName,dto.userCheck.username)
    }

    @PostMapping("/file/read")
    fun readFile(@RequestBody dto: CreateFileDto):String{
        return userService.readFile(dto.userCheck.username,dto.fileName)
    }

    @PostMapping("/file/write")
    fun writeFile(@RequestBody dto:WriteFileDto){
        return userService.writeFile(dto.userCheck.username,dto.fileName,dto.text)
    }

    @PostMapping("/file/send_delete_req")
    fun sendDeleteReq(@RequestBody dto: CreateFileDto){
        userService.sendDeleteRequest(dto.userCheck.username,dto.fileName)
    }
}