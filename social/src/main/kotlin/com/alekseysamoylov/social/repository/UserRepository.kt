package com.alekseysamoylov.social.repository

import com.alekseysamoylov.social.entity.User
import org.springframework.data.jpa.repository.JpaRepository


interface UserRepository : JpaRepository<User, Long> {

    fun findByUsername(username: String): User
}
