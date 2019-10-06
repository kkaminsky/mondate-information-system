package com.kkaminsky.mondateinformationsystem.security

import org.springframework.stereotype.Service
import java.security.Signature
import javax.annotation.PostConstruct
import java.security.KeyPairGenerator
import java.security.SecureRandom
import java.util.*


@Service
class SignatureService {

    val signatureSign: Signature = Signature.getInstance("SHA256WithDSA")

    val signatureVerify: Signature =  Signature.getInstance("SHA256WithDSA")

    @PostConstruct
    fun init(){
        val secureRandom = SecureRandom()
        val keyPairGenerator = KeyPairGenerator.getInstance("DSA")
        val keyPair = keyPairGenerator.generateKeyPair()

        signatureSign.initSign(keyPair.private, secureRandom)

        signatureVerify.initVerify(keyPair.public)
    }

    fun getSign(text: String): ByteArray {

        signatureSign.update(Base64.getEncoder().encode(text.toByteArray()))

        val str =signatureSign.sign()
        println("login text $text")
        println("login sign $str")
        println("________________")
        return str

    }

    fun verify(text: String, sign: ByteArray): Boolean {

        signatureVerify.update(Base64.getEncoder().encode(text.toByteArray()))
        //println(Base64.getEncoder().encode(text.toByteArray()))
        println("check text $text")
        println("check sign $sign")

        println("________++++________")
        val result = signatureVerify.verify(sign)
        println("result $result")
        return result
    }
}