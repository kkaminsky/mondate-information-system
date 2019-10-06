package com.kkaminsky.mondateinformationsystem.repository

import com.kkaminsky.mondateinformationsystem.model.FileEntity
import org.springframework.data.jpa.repository.JpaRepository

interface FileRepository:JpaRepository<FileEntity, Long> {
    fun findByFileName(fileName: String): FileEntity?
}