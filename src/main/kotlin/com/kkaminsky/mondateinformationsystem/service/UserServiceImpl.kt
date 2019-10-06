package com.kkaminsky.mondateinformationsystem.service

import com.kkaminsky.mondateinformationsystem.dto.FileDto
import com.kkaminsky.mondateinformationsystem.dto.RequestDto
import com.kkaminsky.mondateinformationsystem.dto.UserCheckDto
import com.kkaminsky.mondateinformationsystem.dto.UserDto
import com.kkaminsky.mondateinformationsystem.model.RequestEntity
import com.kkaminsky.mondateinformationsystem.model.RoleEntity
import com.kkaminsky.mondateinformationsystem.model.UserEntity
import com.kkaminsky.mondateinformationsystem.repository.FileRepository
import com.kkaminsky.mondateinformationsystem.repository.RequestRepository
import com.kkaminsky.mondateinformationsystem.repository.RoleRepository
import com.kkaminsky.mondateinformationsystem.repository.UserRepository
import com.kkaminsky.mondateinformationsystem.security.SignatureService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class UserServiceImpl @Autowired constructor(
        val encoder: PasswordEncoder,
        val userRepository: UserRepository,
        val fileRepository: FileRepository,
        val roleRepository: RoleRepository,
        val signatureService: SignatureService,
        val requestRepository: RequestRepository
) : UserService {

    override fun editFile(fileName: String, level: Int) {

        val file = fileRepository.findByFileName(fileName)?:throw Exception("Файл $fileName не найден!")

        file.level = level

        fileRepository.save(file)

    }

    override fun editUser(username: String, level: Int) {
        val user = userRepository.findByUsername(username)?:throw Exception("Пользователь $username не существует!")

        user.level = level

        userRepository.save(user)
    }

    override fun getAllUsers(): List<UserDto> {

        return userRepository.findAll().map {
            UserDto(
                    username = it.username!!,
                    userRole = it.role!!.role!!,
                    level = it.level!!
            )
        }
    }


    override fun getRequests(): List<RequestDto> {

        return requestRepository.findByDone(true).map {
            RequestDto(
                    fileName = it.file!!.fileName!!,
                    ownerName =it.user!!.username!!
            )
        }

    }

    override fun deleteFile(fileName: String) {

        val file = fileRepository.findByFileName(fileName)?:throw Exception("Файл $fileName не найден!")

        val req = requestRepository.findByFile(file)?:throw Exception("Нет запроса на удаление данного файла!")

        fileRepository.delete(file)

        req.done = true

    }

    override fun sendDeleteRequest(username: String, fileName: String) {

        val user = userRepository.findByUsername(username)?:throw Exception("Пользователь $username не существует!")
        val file = fileRepository.findByFileName(fileName)?:throw Exception("Файл $fileName не найден!")

        if(file.owner!=user) throw Exception("Вы не являетесь владельцем файла!")

        val req = RequestEntity()
        req.file = file
        req.user = user
        req.done = false

        requestRepository.save(req)
    }

    override fun writeFile(username: String,fileName: String,text:String){
        val user = userRepository.findByUsername(username)?:throw Exception("Пользователь $username не существует!")
        val file = fileRepository.findByFileName(fileName)?:throw Exception("Файл $fileName не найден!")

        if(user.level!!>file.level!!) throw Exception("Вам запрещен доступ к этому файлу!")

        file.text+=text

        fileRepository.save(file)

    }

    override fun readFile(username: String, fileName: String): String {

        val user = userRepository.findByUsername(username)?:throw Exception("Пользователь $username не существует!")
        val file = fileRepository.findByFileName(fileName)?:throw Exception("Файл $fileName не найден!")

        if(user.level!!<file.level!!) throw Exception("Вам запрещен доступ к этому файлу!")

        return file.text
    }

    override fun getFilesForUser(username: String): List<FileDto> {

        val user = userRepository.findByUsername(username) ?: throw Exception("Пользователя $username не существует!")

        return fileRepository.findAll().map {

            val readFlag = it.level!! >= user.level!!
            val writeFlag = it.level!! <= user.level!!
            val deleteFlag = it.owner!!.id == user.id
            val requestFlag = requestRepository.findByFile(it) != null

            FileDto(
                    fileName = it.fileName!!,
                    readFlag = readFlag,
                    writeFlag = writeFlag,
                    deleteFlag = deleteFlag,
                    requestFlag = requestFlag
            )
        }
    }

    override fun register(username: String, password: String) {

        val user = userRepository.findByUsername(username)

        if (user != null) throw Exception("Пользователь $username уже существует!")

        val newUser = UserEntity()
        newUser.username = username
        newUser.password = encoder.encode(password)
        newUser.level = 0

        var role = roleRepository.findByRole("ROLE_USER")

        if (role == null) {
            role = RoleEntity()
            role.role = "ROLE_USER"
            roleRepository.save(role)
        }

        newUser.role = role

        userRepository.save(newUser)
    }

    override fun registerAdmin(username: String, password: String) {

        val user = userRepository.findByUsername(username)

        if (user != null) throw Exception("Пользователь $username уже существует!")

        val newUser = UserEntity()
        newUser.username = username
        newUser.password = encoder.encode(password)
        newUser.level = 10

        var role = roleRepository.findByRole("ROLE_ADMIN")

        if (role == null) {
            role = RoleEntity()
            role.role = "ROLE_ADMIN"
            roleRepository.save(role)
        }

        newUser.role = role

        userRepository.save(newUser)
    }

    override fun login(username: String, password: String): UserCheckDto {

        val user = userRepository.findByUsername(username) ?: throw Exception("Пользователя $username не существует!")

        if (encoder.matches(password, user.password)) {
            return UserCheckDto(
                    username = user.username!!,
                    userRole = user.role!!.role!!,
                    signature = signatureService.getSign(user.username!! + user.role!!.role!!)
            )
        } else {
            throw Exception("Неверный пароль!")
        }
    }
}