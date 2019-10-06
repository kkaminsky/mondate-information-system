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

    @PostMapping("/get/files")
    fun getFilesForUser(@RequestBody dto:GetFilesDto):List<FileDto>{
        return userService.getFilesForUser(dto.userCheck.username)
    }
}