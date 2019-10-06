package com.kkaminsky.mondateinformationsystem.security

import com.kkaminsky.mondateinformationsystem.dto.UserCheckDto
import com.kkaminsky.mondateinformationsystem.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component("securityService")
class SecurityService @Autowired constructor(
        val signatureService: SignatureService,
        val userRepository: UserRepository
) {

    fun hasPermission(userCheck: UserCheckDto): Boolean {
        if(!signatureService.verify(userCheck.username+userCheck.userRole,userCheck.signature)){
            return false
        }
        else{

            val userRep = userRepository.findByUsername(userCheck.username)?:return false

            return userRep.role!!.role == userCheck.userRole

        }
    }

}