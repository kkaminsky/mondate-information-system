package com.kkaminsky.mondateinformationsystem.repository

import com.kkaminsky.mondateinformationsystem.model.FileEntity
import com.kkaminsky.mondateinformationsystem.model.RequestEntity
import org.springframework.data.jpa.repository.JpaRepository

interface RequestRepository:JpaRepository<RequestEntity,Long> {

    fun findByFile(file: FileEntity): RequestEntity?
    fun findByDone(done: Boolean):List<RequestEntity>
}