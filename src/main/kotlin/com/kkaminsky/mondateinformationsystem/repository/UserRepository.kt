package com.kkaminsky.mondateinformationsystem.repository

import com.kkaminsky.mondateinformationsystem.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<UserEntity, Long> {

    fun findByUsername(username: String): UserEntity?

}