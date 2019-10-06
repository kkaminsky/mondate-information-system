package com.kkaminsky.mondateinformationsystem.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain

import org.springframework.security.config.annotation.web.builders.HttpSecurity





@Configuration
@EnableWebSecurity
class SecurityConfig: WebSecurityConfigurerAdapter() {


    override fun configure(security: HttpSecurity) {
        security.httpBasic().disable()
        security.csrf().disable()
        security.formLogin().disable()
        security.logout().disable()
    }


}