package com.kkaminsky.mondateinformationsystem.repository

import com.kkaminsky.mondateinformationsystem.model.RoleEntity
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository: JpaRepository<RoleEntity, Long> {
    fun findByRole(role: String): RoleEntity?
}