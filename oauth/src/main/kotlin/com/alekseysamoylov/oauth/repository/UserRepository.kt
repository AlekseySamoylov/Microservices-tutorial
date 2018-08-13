package com.alekseysamoylov.oauth.repository

import com.alekseysamoylov.oauth.domain.User
import org.springframework.stereotype.Repository


@Repository
class UserRepository {

    private val userMap: MutableMap<String, User> = HashMap()
    fun createUser(user: User) {
        userMap[user.email] = user
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
